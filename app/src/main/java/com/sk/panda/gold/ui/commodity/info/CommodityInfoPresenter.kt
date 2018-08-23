package com.sk.panda.gold.ui.commodity.info

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.Commodity

/**
 * 标的详情信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityInfoPresenter(v: CommodityInfoContract.View) :
        PresenterDataWrapper<Commodity, CommodityInfoContract.View>(v),
        CommodityInfoContract.Presenter<Commodity> {
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): Commodity {
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