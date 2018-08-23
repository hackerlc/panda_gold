package com.sk.panda.gold.ui.gold

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 金价趋势
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class GoldTrendPresenter(v: GoldTrendContract.View) :
        PresenterDataWrapper<String, GoldTrendContract.View>(v),
        GoldTrendContract.Presenter<String> {
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