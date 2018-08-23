package com.sk.panda.gold.ui.message.platform

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 平台通知
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class PlatformMessagePresenter(v: PlatformMessageContract.View) :
        PresenterDataWrapper<String, PlatformMessageContract.View>(v),
        PlatformMessageContract.Presenter<String> {
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