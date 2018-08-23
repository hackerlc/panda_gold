package com.sk.panda.gold.ui.success

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 操作成功页面
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class SuccessPagePresenter(v: SuccessPageContract.View) :
        PresenterDataWrapper<String, SuccessPageContract.View>(v),
        SuccessPageContract.Presenter<String> {
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