package com.sk.panda.gold.ui.commodity.choose.spec

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 选择规格
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class ChooseSpecPresenter(v: ChooseSpecContract.View) :
        PresenterDataWrapper<String, ChooseSpecContract.View>(v),
        ChooseSpecContract.Presenter<String> {
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