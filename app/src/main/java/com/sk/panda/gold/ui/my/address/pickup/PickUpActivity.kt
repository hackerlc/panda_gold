package com.sk.panda.gold.ui.my.address.pickup

import android.content.Context
import android.content.Intent
import android.view.View
import com.baidu.location.BDLocation
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.sk.panda.gold.utils.bd.MyLocationListener
import gear.yc.com.gearlibrary.rxjava.rxbus.RxBus
import gear.yc.com.gearlibrary.rxjava.rxbus.annotation.Subscribe
import kotlinx.android.synthetic.main.activity_pick_up.*


/**
 * 自提
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class PickUpActivity :
        BasePActivity<PickUpContract.Presenter<String>>(R.layout.activity_pick_up),
        PickUpContract.View {
    var mLocationClient: LocationClient? = null
    private val myListener = MyLocationListener()

    companion object {
        fun startAct(context: Context) {
            context.startActivity(Intent(context, PickUpActivity::class.java))
        }
    }

    override fun attachPresenter() {
        RxBus.getInstance().register(this)
        mPresenter = PickUpPresenter(this)
    }

    override fun initUI() {
    }

    override fun initData() {
        mLocationClient = LocationClient(applicationContext)
        //声明LocationClient类
        mLocationClient!!.registerLocationListener(myListener)
        //注册监听函数

        var option = LocationClientOption()

        option.setIsNeedAddress(true)
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient!!.setLocOption(option)
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        mLocationClient!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.getInstance().unRegister(this)
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun updateUI() {
    }

    @Subscribe(tag = RxBus.TAG_UPDATE)
    private fun updateAddress(location: BDLocation){
        tv_address.text = location.city
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this, str)
    }

    override fun onError(error: Throwable) {

    }

    override fun onDialog(show: Boolean) {
        if (show) {
            ProgressDialogUtil.getInstance().build(this).show()
        } else {
            ProgressDialogUtil.getInstance().dismiss()
        }
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }
}