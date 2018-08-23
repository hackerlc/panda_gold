package com.sk.panda.gold.ui.login

import com.sk.panda.gold.base.contract.BaseContract
import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 登录
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class LoginPresenter(v : BaseContract.BaseView) :
        PresenterDataWrapper<String, BaseContract.BaseView>(v),
        LoginContract.Presenter<String> {
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): String {
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