package com.xialan.beautymall.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.data.ImagePickerCropParams;
import com.tamic.novate.util.FileUtil;
import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserIdenBean;
import com.xialan.beautymall.contract.UpdateIDENContract;
import com.xialan.beautymall.presenter.UpdateIDENPresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/12/13.
 */
public class UpdateIDENActivity extends BaseActivity implements UpdateIDENContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.et_user_real_name)
    EditText etUserRealName;
    @BindView(R.id.et_user_tel)
    EditText etUserTel;
    @BindView(R.id.rb_iden_type)
    RadioButton rbIdenType;
    @BindView(R.id.rb_passport_type)
    RadioButton rbPassportType;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.et_iden_num)
    EditText etIdenNum;
    @BindView(R.id.iv_font_pic)
    ImageView ivFontPic;
    @BindView(R.id.iv_bg_pic)
    ImageView ivBgPic;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    private UpdateIDENPresenter updateIDENPresenter;
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
    private ImagePicker imagePicker;
    private String[] perms;
    private String type = "99";
    private boolean camera_state_1 = false;
    private boolean camera_state_2 = false;
    private File frontfile = null;
    private File bgfile = null;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_upload_iden;
    }

    @Override
    protected void initData() {


        imagePicker = new ImagePicker();
        perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(this, "调用相机所必须的权限", 0, perms);
        }
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_iden_type:
                        //身份证
                        type = "0";
                        break;
                    case R.id.rb_passport_type:
                        //护照
                        type = "1";
                        break;
                }
            }
        });
        //是否获取数据
        if (MyApplaction.UpdateIden) {
            updateIDENPresenter.getUserInfo(MyApplaction.getInstance().getUserInfoBean().getUid());
            MyApplaction.UpdateIden = false;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        updateIDENPresenter = new UpdateIDENPresenter(this);
        return updateIDENPresenter;
    }

    @Override
    public void upDataSuccessed() {
        UIUtils.showToast("证件信息上传成功!我们会尽快为您审核");
        finish();
    }

    @Override
    public void upDataFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void getUserIdenSuccessed(UserIdenBean.DataBean dataBean) {
        //获取数据成功
        if (dataBean.getIden_no().equals("")) {
            //判断是否有认证 没有认证
            return;
        } else {
            tvDelete.setVisibility(View.VISIBLE);
            if (dataBean.getIden_state().equals("0")){
                tvDelete.setText("审核中");
            }else{
                tvDelete.setText("已通过");
            }
            //认证过 开始回显数据
            etUserRealName.setText(dataBean.getReal_name());
            etIdenNum.setText(dataBean.getIden_no());
            switch (dataBean.getIden_type()) {
                case "0":
                    rbIdenType.setChecked(true);
                    break;
                case "1":
                    rbPassportType.setChecked(true);
                    break;
                default:
                    break;
            }
            try {
                ImageLoaderManager.displayImage("http://www.gou-mei.com/IBSync/new/identification/"+dataBean.getFront_img(),ivFontPic);
                ImageLoaderManager.displayImage("http://www.gou-mei.com/IBSync/new/identification/"+dataBean.getBg_img(),ivBgPic);
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void getUserIdenFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_font_pic, R.id.iv_bg_pic, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_font_pic:
                camera_state_1 = true;
                camera_state_2 = false;
                // 正面照片
                showDialog();
                break;
            case R.id.iv_bg_pic:
                camera_state_1 = false;
                camera_state_2 = true;
                //侧面照片
                showDialog();
                break;
            case R.id.btn_upload:
                String iden_name = etUserRealName.getText().toString().trim();
                String iden_no = etIdenNum.getText().toString().trim();
                if (type.equals("99")) {
                    UIUtils.showToast("还没有选择上传的证件类型");
                    return;
                }
                if (StringUtil.isEmpty(iden_name)) {
                    UIUtils.showToast("还没有输入真时姓名");
                    return;
                }
                if (StringUtil.isEmpty(iden_no)) {
                    UIUtils.showToast("还没有输入身份证号");
                    return;
                }
                if (StringUtil.isEmpty(iden_no)) {
                    UIUtils.showToast("证件编号不能为空!");
                    return;
                }
                if (type.equals("0")) {
                    if (!UIUtils.isLegalId(iden_no)) {
                        UIUtils.showToast("身份证号码有误,请检查,如果问题请联系管理员");
                        return;
                    }
                }
                if (frontfile == null) {
                    UIUtils.showToast("还没有上传证件正面照!");
                    return;
                }

                if (bgfile == null) {
                    UIUtils.showToast("还没有上传证件背面照!");
                    return;
                }
                //验证
                String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
                updateIDENPresenter.upDataForUserIden(uid, iden_name, iden_no, type, frontfile, bgfile);
                break;
        }
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
        if (camera_state_1) {
            frontfile = new File(imagePath);
            ivFontPic.setImageBitmap(bitmapByUri);
            camera_state_1 = false;
        } else {
            bgfile = new File(imagePath);
            ivBgPic.setImageBitmap(bitmapByUri);
            camera_state_2 = false;
        }
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
                        .doCrop(new ImagePickerCropParams(16, 9, 1920, 1080))
                        .start(UpdateIDENActivity.this, REQ_CODE_PICTURE);
                dialog.dismiss();
            }
        });
        //选择拍照
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.pickType(ImagePickType.ONLY_CAMERA)
                        .doCrop(new ImagePickerCropParams(16, 9, 1920, 1080))
                        .start(UpdateIDENActivity.this, REQ_CODE_CAMERA);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        UIUtils.showToast("已获得拍照权限");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("实名认证");
        setIVReturn();
    }
}
