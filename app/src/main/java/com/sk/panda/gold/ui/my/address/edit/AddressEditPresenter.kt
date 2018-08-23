package com.sk.panda.gold.ui.my.address.edit

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.Address

/**
 * 收货地址编辑
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class AddressEditPresenter(v: AddressEditContract.View) :
        PresenterDataWrapper<Address, AddressEditContract.View>(v),
        AddressEditContract.Presenter<Address> {
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): Address {
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