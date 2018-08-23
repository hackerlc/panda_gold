package com.sk.panda.gold.ui.web

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.config.AppConfig
import com.sk.panda.gold.entity.UpApp
import com.sk.panda.gold.entity.User
import com.sk.panda.gold.net.helper.SchedulersDataHelper
import com.sk.panda.gold.net.model.CommonNM
import com.sk.panda.gold.net.model.UserNM
import com.sk.panda.gold.utils.SPUtil
import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper

/**
 * 查看网页
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class WebPresenter(v: WebContract.View) :
        PresenterDataWrapper<User, WebContract.View>(v),
        WebContract.Presenter<User> {
    val mNM = UserNM()
    val mCommonNM = CommonNM()
    override fun fetch() {
        if (AppConfig.I_USER?.phone != null) {
            mNM.onLogin(AppConfig.I_USER?.phone!!, "", "")
                    .compose(mView.get()?.getLifecycleDestroy())
                    .compose(RxSchedulersHelper.io_main())
                    .compose(SchedulersDataHelper.handleResult())
                    .subscribe({ processData(it) },
                            { errorData(it) })
        } else {
            errorData(Throwable("手机号未能找到"))
        }
    }

    override fun processData(d: User) {
        //登录成功保存用户信息
        AppConfig.I_USER = d
        SPUtil.saveObj(d, SPUtil.USER_PSD)
        mView.get()?.updateUI()
    }

    override fun upDataApp() {
        mCommonNM.upDataApp()
                .compose(mView.get()!!.getLifecycleDestroy())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersDataHelper.handleResult())
                .subscribe({ processAppData(it) },
                        { errorData(it) })
    }

    private fun processAppData(data: UpApp){
        mView.get()?.upDataApp(data)
    }

    override fun refreshData() {
    }

    override fun getData(): User {
        return mData
    }

    override fun errorData(error: Throwable) {
        super.errorData(error)
        mView.get()?.onError(error)
    }

    override fun close() {
        mView.clear()
    }
}