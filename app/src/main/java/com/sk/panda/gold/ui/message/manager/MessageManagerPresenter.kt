package com.sk.panda.gold.ui.message.manager

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper

/**
 * 消息中心
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class MessageManagerPresenter(v: MessageManagerContract.View) :
        PresenterDataWrapper<String, MessageManagerContract.View>(v),
        MessageManagerContract.Presenter<String> {
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