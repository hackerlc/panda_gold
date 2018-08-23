package com.sk.panda.gold.ui.commodity.more

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 标的详情更多信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityMorePresenter(v: CommodityMoreContract.View) :
        PresenterDataWrapper<String, CommodityMoreContract.View>(v),
        CommodityMoreContract.Presenter<String> {
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