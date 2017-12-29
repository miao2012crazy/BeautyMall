package com.xialan.beautymall.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.data.ImagePickerCropParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.UserinfoManageContract;
import com.xialan.beautymall.http.HttpUrl;
import com.xialan.beautymall.presenter.UserinfoManagePresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.UIUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UserinfoManageActivity extends BaseActivity implements UserinfoManageContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.tv_user_head_img)
    ImageView tvUserHeadImg;
    @BindView(R.id.card_user_info)
    LinearLayout cardUserInfo;
    @BindView(R.id.et_user_nick_name)
    EditText tvUserNickName;

    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.card_user_id)
    CardView cardUserId;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.card_user_sex)
    LinearLayout cardUserSex;
    @BindView(R.id.et_user_age)
    TextView tvUserAge;

    @BindView(R.id.tv_user_tel)
    TextView tvUserTel;
    @BindView(R.id.card_user_tel)
    LinearLayout cardUserTel;
    private UserinfoManagePresenter userinfoManagePresenter;
    private UserInfoBean.DataBean userInfoBean;
    private ImagePicker imagePicker;
    // 拍照
    private final int REQ_CODE_CAMERA = 21;
    // 相册
    private final int REQ_CODE_PICTURE = 22;
    // 裁图
    private final int REQ_CODE_CUT = 23;
    public final Uri imgUrl = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
            .getPath() + File.separator + "id.jpg"));//临时储存的Uri
    public final Uri imgUrl1 = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
            .getPath() + File.separator + "id_2.jpg"));//临时储存的Uri
    private File file = null;
    private String sexState = "";

    @Override
    protected int setlayoutID() {
        return R.layout.activity_user_info_manage;
    }

    @Override
    protected void initData() {
        imagePicker = new ImagePicker();
        userInfoBean = MyApplaction.userInfoBean;
        tvUserSex.setText(getUserSex());
        sexState = userInfoBean.getUser_sex();
        tvUserAge.setText(userInfoBean.getUser_age());
        tvUserNickName.setText(userInfoBean.getUser_name());
        tvUserTel.setText(userInfoBean.getUid());
        ImageLoaderManager.displayHeadIcon(HttpUrl.baseUrl_IB()+"IBSync/new/client_profile/"+userInfoBean.getUser_img(),tvUserHeadImg);
    }

    private String getUserSex() {
        String user_sex = userInfoBean.getUser_sex();
        if (user_sex.equals("0")) {
            return "男";
        } else {
            return "女";
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        userinfoManagePresenter = new UserinfoManagePresenter(this);
        return userinfoManagePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.card_user_info, R.id.card_user_sex, R.id.card_user_tel, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_user_info:
                //TODO 头像
                showDialog();
                break;
            case R.id.card_user_sex:
                showDialogSex();
                break;
            case R.id.card_user_tel:
                //TODO 电话
                UIUtils.showToast("暂不支持手机号码的修改!");
                break;
            case R.id.btn_commit:
                userinfoManagePresenter.updateUserInfo(userInfoBean.getUid(),tvUserNickName.getText().toString(),tvUserAge.getText().toString(),sexState,file);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("个人资料");
        setIVReturn();
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.camera_dialog, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        //选择相片
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.pickType(ImagePickType.SINGLE)
                        .doCrop(new ImagePickerCropParams(1, 1, 480, 480))
                        .start(UserinfoManageActivity.this, REQ_CODE_PICTURE);
                dialog.dismiss();
            }
        });
        //选择拍照
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.pickType(ImagePickType.ONLY_CAMERA)
                        .doCrop(new ImagePickerCropParams(1, 1, 480, 480))
                        .start(UserinfoManageActivity.this, REQ_CODE_CAMERA);
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        Display d = dialogWindow.getWindowManager().getDefaultDisplay();
        lp.width = (int) (d.getWidth());
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }


    private void showDialogSex() {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_sex, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        //选择相片
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexState = "1";
                tvUserSex.setText("女");
                dialog.dismiss();
            }
        });
        //选择拍照
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexState = "0";
                tvUserSex.setText("男");
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        Display d = dialogWindow.getWindowManager().getDefaultDisplay();
        lp.width = (int) (d.getWidth());
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_CODE_CAMERA:
                    //获取选择的图片数据
                    List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
                    String imagePath = resultList.get(0).getImagePath();
                    Bitmap bitmap1 = BitmapFactory.decodeFile(imagePath);
                    setpic(bitmap1, imagePath);
                    break;
                case REQ_CODE_PICTURE:
                    List<ImageBean> resultList2 = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
                    String imagePath2 = resultList2.get(0).getImagePath();
                    Bitmap bitmap2 = BitmapFactory.decodeFile(imagePath2);
                    setpic(bitmap2, imagePath2);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 设置图片
     *
     * @param bitmapByUri
     */
    private void setpic(Bitmap bitmapByUri, String imagePath) {
        file = new File(imagePath);
//        tvUserHeadImg.setImageBitmap(bitmapByUri);
        Uri uri = Uri.fromFile(file);
        ImageLoader.getInstance().displayImage(uri.toString(),tvUserHeadImg,ImageLoaderManager.getOptions4Header());
    }

    @Override
    public void updateInfoSuccessed() {
        UIUtils.showToast("修改成功!");
        finish();
    }

    @Override
    public void updateInfoFailed(String err_msg) {
        UIUtils.showToast("修改失败!");
    }


}
