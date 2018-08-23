package com.sk.panda.gold.ui.my.address.pickup

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 自提
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class PickUpPresenter(v: PickUpContract.View) :
        PresenterDataWrapper<String, PickUpContract.View>(v),
        PickUpContract.Presenter<String> {
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