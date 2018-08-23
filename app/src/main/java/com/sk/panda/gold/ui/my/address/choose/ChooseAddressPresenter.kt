package com.sk.panda.gold.ui.my.address.choose

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 选择收货地址
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class ChooseAddressPresenter(v: ChooseAddressContract.View) :
        PresenterDataWrapper<String, ChooseAddressContract.View>(v),
        ChooseAddressContract.Presenter<String> {
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