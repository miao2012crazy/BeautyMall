package com.xialan.beautymall.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.contract.AddNewAddressContract;
import com.xialan.beautymall.presenter.AddNewAddressPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AddNewAddressActivity extends BaseActivity implements AddNewAddressContract.View {

    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_consignee_tel)
    EditText etConsigneeTel;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    private AddNewAddressPresenter addNewAddressPresenter;
    private String isDeaufalt = "0";
    private AddressBean.DataBean addressbean;
    private String address_no;
    private boolean isUpdate = false;
    private String item;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_add_new_address;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        addNewAddressPresenter = new AddNewAddressPresenter(this);
        return addNewAddressPresenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean updateAddress = MyApplaction.updateAddress;
        if (updateAddress) {
            addressbean = MyApplaction.getAddressBean();
            if (addressbean == null) return;
            etConsigneeTel.setText(addressbean.getAddress_user_tel());
            etAddressDetail.setText(addressbean.getAddress_class_b());
            etConsignee.setText(addressbean.getRecv_title());
            etAddress.setText(addressbean.getAddress_class_a());
            //TODO 默认选中状态
            if (addressbean.getIs_default().equals("1")) {
                cbCheck.setChecked(true);
            }
            tvTitle.setText("修改地址");
            isUpdate = true;
        } else {
            tvTitle.setText("新增地址");
            isUpdate = false;
        }
        MyApplaction.addressbean = null;

        tvDelete.setVisibility(View.VISIBLE);
        tvDelete.setText("保存");
        setIVReturn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_delete)
    public void onViewClicked() {
        //保存地址
        String user_name = etConsignee.getText().toString();
        String user_tel = etConsigneeTel.getText().toString();

        String user_address_a = etAddress.getText().toString();
        String user_address_b = etAddressDetail.getText().toString();
        if (cbCheck.isChecked()) {
            isDeaufalt = "1";
        } else {
            isDeaufalt = "0";
        }
        String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        if (addressbean != null) {
            //地址id
            address_no = addressbean.getAddress_no();
        }
        //修改地址 和 添加新地址
        if (isUpdate) {
            //修改地址
            item = "0";
        } else {
            //添加新地址
            item = "1";
        }
        addNewAddressPresenter.upDateNewDataToNet(item, uid, address_no, user_name, user_tel, user_address_a, user_address_b, "", isDeaufalt);
    }

    @Override
    public void onUpDataSuccessed() {
        //修改地址 和 添加新地址
        if (isUpdate) {
            //修改地址
            UIUtils.showToast("修改成功!");
        } else {
            //添加新地址
            UIUtils.showToast("添加成功!");
        }
        finish();
    }

    @Override
    public void onUpDataFailed(String msg) {
        UIUtils.showToast("上传失败!" + ParseUtils.showErrMsg(msg));
    }
}
