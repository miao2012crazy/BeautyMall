package com.xialan.beautymall.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.maning.updatelibrary.InstallUtils;
import com.xialan.beautymall.bean.UpdateAppBean;
import com.xialan.beautymall.http.HttpUrl;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/28.
 */
public class UpdateAppManager {
    // 外存sdcard存放路径
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/" + "AutoUpdate" + "/";
    // 下载应用存放全路径
    private static final String FILE_NAME = "AutoUpdate.apk";
    // 准备安装新版本应用标记
    private static final int INSTALL_TOKEN = 1;
    //Log日志打印标签
    private static final String TAG = "Update_log";
    private Context context;
    //获取版本数据的地址
    private String version_path = HttpUrl.setGetUrl("/IBSync/new/search_version_update.ashx");
    //获取新版APK的默认地址
    private String apk_path = HttpUrl.setGetUrl("APPSync/app_release/");
    // 下载应用的进度条
    private ProgressDialog progressDialog;

    //新版本号和描述语言
    private int update_versionCode;


    public UpdateAppManager(Context context) {
        this.context = context;
    }

    /**
     * 获取当前版本号
     */
    private int getCurrentVersion() {
        try {

            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);


            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

    /**
     * 从服务器获得更新信息
     */
    public void getUpdateMsg() {
        class mAsyncTask extends AsyncTask<String, Integer, String> {
            @Override
            protected String doInBackground(String... params) {

                HttpURLConnection connection = null;
                try {
                    URL url_version = new URL(params[0]);
                    connection = (HttpURLConnection) url_version.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));


                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    return response.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                try { //回到主线程，更新UI
                    Gson gson = new Gson();
                    UpdateAppBean updateAppBean = gson.fromJson(s, UpdateAppBean.class);
                    boolean checkdata = ParseUtils.checkdata(updateAppBean.getCode());
                    if (checkdata){
                        update_versionCode = Integer.parseInt(updateAppBean.getData().getVersion_code());
                        apk_path = HttpUrl.setGetUrl("/APPSync/app_release/" + updateAppBean.getData().getFile_path());
                        int currentVersion = getCurrentVersion();
                        if (update_versionCode > currentVersion) {
                            showNoticeDialog();
                        } else {
                        }
                    }else{
                        UIUtils.showToast(ParseUtils.showErrMsg(updateAppBean.getErr_msg()));
                    }
                } catch (Exception e) {
                    update_versionCode = getCurrentVersion();
                }
            }
        }
        new mAsyncTask().execute(version_path);
    }


    /**
     * 显示提示更新对话框
     */
    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("检测到新版本！")
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog();
                    }
                })
                .create().show();
    }

    /**
     * 显示下载进度对话框
     */
    public void showDownloadDialog() {
        new InstallUtils(context, apk_path, FILE_NAME, new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("正在下载新版本...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

            @Override
            public void onComplete(String path) {
/**
 * 安装APK工具类
 * @param context       上下文
 * @param filePath      文件路径
 * @param authorities   ---------Manifest中配置provider的authorities字段---------
 * @param callBack      安装界面成功调起的回调
 */
                InstallUtils.installAPK(context, path, context.getPackageName() + ".fileProvider", new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onLoading(long total, long current) {
                progressDialog.setProgress(Integer.parseInt(String.valueOf(current * 100 / total)));
            }

            @Override
            public void onFail(Exception e) {
                UIUtils.showToast("下载失败!");
            }
        }).downloadAPK();


//        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("正在下载新版本...");
//
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setCanceledOnTouchOutside(false);
//        new downloadAsyncTask().execute();
    }

    /**
     * 下载新版本应用
     */
    private class downloadAsyncTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {


            URL url;
            HttpURLConnection connection = null;
            InputStream in = null;
            FileOutputStream out = null;
            try {
                url = new URL(apk_path);
                connection = (HttpURLConnection) url.openConnection();

                in = connection.getInputStream();
                long fileLength = connection.getContentLength();
                File file_path = new File(FILE_PATH);
                if (!file_path.exists()) {
                    file_path.mkdir();
                }

                out = new FileOutputStream(new File(FILE_NAME));//为指定的文件路径创建文件输出流
                byte[] buffer = new byte[1024 * 1024];
                int len = 0;
                long readLength = 0;


                while ((len = in.read(buffer)) != -1) {

                    out.write(buffer, 0, len);//从buffer的第0位开始读取len长度的字节到输出流
                    readLength += len;

                    int curProgress = (int) (((float) readLength / fileLength) * 100);


                    publishProgress(curProgress);

                    if (readLength >= fileLength) {

                        break;
                    }
                }

                out.flush();
                return INSTALL_TOKEN;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressDialog.dismiss();//关闭进度条
            //安装应用
            installApp();
        }
    }

    /**
     * 安装新版本应用
     */
    private void installApp() {
        File appFile = new File(FILE_NAME);
        try {
            if (!appFile.exists()) {
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + appFile.toString()), "application/vnd.android.package-archive");
            context.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + appFile.toString()), "application/vnd.android.package-archive");
            context.startActivity(intent);
            e.printStackTrace();
        }
    }


}
