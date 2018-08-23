package com.sk.panda.gold.ui.my.address.distribution

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 配送方式
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class DistributionTypePresenter(v: DistributionTypeContract.View) :
        PresenterDataWrapper<String, DistributionTypeContract.View>(v),
        DistributionTypeContract.Presenter<String> {
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