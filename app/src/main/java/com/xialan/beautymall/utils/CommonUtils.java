package com.xialan.beautymall.utils;

import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/12/7.
 */

public class CommonUtils {
    /**
     * 获取订单状态
     *
     * @param state
     * @return
     */
    public static String getOrderState(String state) {
        switch (state) {
            case "0":
//                return "待下单";
                return "待付款";
            case "1":
                return "已下单";
            case "2":
                return "待收货";
            case "3":
                return "已完交易";
            case "4":
                return "待取消";
            case "5":
                return "待退货";
            case "6":
                return "已退货";
            case "7":
                return "已取消";
            default:
                return "";
        }
    }

    /**
     * 按钮显示状态
     *
     * @param order_state
     */
    public static   void checkBtnShowState(String order_state, Button btnPay,Button btnUpdateAddress,Button btnCancelOrder) {
        switch (order_state) {
            case "0":
                break;
            case "1":
                btnPay.setVisibility(View.GONE);
                break;
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
                btnPay.setVisibility(View.GONE);
                btnUpdateAddress.setVisibility(View.GONE);
                btnCancelOrder.setVisibility(View.GONE);
            default:
                break;
        }

    }


    public static String getOrderStateForDetail(String state) {
        switch (state) {
            case "0":
                return "待下单";
//                return "待付款";
            case "1":
                return "已下单";
            case "2":
                return "待收货";
            case "3":
                return "已完交易";
            case "4":
                return "待取消";
            case "5":
                return "待退货";
            case "6":
                return "已退货";
            case "7":
                return "已取消";
            default:
                return "";
        }
    }
    /**
     * 取消订单按钮 仅 已下单 状态
     *
     * @param order_state
     * @param pay_state
     * @return
     */
    public static boolean checkCancleBtnShowState(String order_state, String pay_state) {
        if (order_state.equals("4")){
            //已取消状态 不显示任何按钮
            return false;
        }
        if (order_state.equals("1")||order_state.equals("0")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是否能够修改地址
     *
     * @param order_state
     * @param shipment_state
     * @return
     */
    public static int CheckBtnUpdateAddressState(String order_state, String shipment_state) {

        switch (order_state){
            case "0":
//                return "待下单";
                return View.VISIBLE;
            case "1":
                if (shipment_state.equals("0")){
                    return View.VISIBLE;
                }else{
                    return View.GONE;
                }
            case "2":
                return View.GONE;
            case "3":
                return View.GONE;
            case "4":
                return View.GONE;
            case "5":
                return View.GONE;
            case "6":
                return View.GONE;
            case "7":
                return View.GONE;
            default:
                return View.GONE;
        }


    }

    /**
     * 是否显示立即支付按钮
     *
     * @param payState
     * @param payState
     * @return
     */
    public static boolean checkBtnPayShowState(String payState, String order_state) {
        if (order_state.equals("4")){
            //已取消状态 不显示任何按钮
            return false;
        }

        if (payState.equals("0")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是否显示售后服务按钮
     * @param order_state
     * @return
     */
    public static boolean checkBtnServiceShowState(String order_state) {
        if (order_state.equals("3")){
            return true;
        }else{
            return false;
        }
    }
}
