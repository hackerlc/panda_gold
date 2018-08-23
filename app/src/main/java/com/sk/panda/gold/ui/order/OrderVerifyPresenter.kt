package com.sk.panda.gold.ui.order

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.ShoppingGoods

/**
 * 订单确认
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class OrderVerifyPresenter(v: OrderVerifyContract.View) :
        PresenterDataWrapper<ShoppingGoods, OrderVerifyContract.View>(v),
        OrderVerifyContract.Presenter<ShoppingGoods> {
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): ShoppingGoods {
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