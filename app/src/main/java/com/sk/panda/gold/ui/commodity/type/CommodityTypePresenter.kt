package com.sk.panda.gold.ui.commodity.type

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 商品分类
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityTypePresenter(v: CommodityTypeContract.View) :
        PresenterDataWrapper<String, CommodityTypeContract.View>(v),
        CommodityTypeContract.Presenter<String> {
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