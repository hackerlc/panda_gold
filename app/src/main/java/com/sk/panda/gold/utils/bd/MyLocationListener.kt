package com.sk.panda.gold.utils.bd

import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import gear.yc.com.gearlibrary.rxjava.rxbus.RxBus


/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/16
 */
class MyLocationListener : BDAbstractLocationListener() {
    override fun onReceiveLocation(location: BDLocation) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取地址相关的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

//        val addr = location.addrStr    //获取详细地址信息
//        val country = location.country    //获取国家
//        val province = location.province    //获取省份
//        val city = location.city    //获取城市
//        val district = location.district    //获取区县
//        val street = location.street    //获取街道信息

        RxBus.getInstance().post(RxBus.TAG_UPDATE,location)
    }
}