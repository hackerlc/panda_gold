package com.sk.panda.gold.ui.commodity.list

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 商品列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityListPresenter(v: CommodityListContract.View) :
        PresenterDataWrapper<String, CommodityListContract.View>(v),
        CommodityListContract.Presenter<String> {
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