package com.sk.panda.gold.ui.news

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.News

/**
 * 资讯列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class NewsPresenter(v: NewsContract.View) :
        PresenterDataWrapper<MutableList<News>, NewsContract.View>(v),
        NewsContract.Presenter<MutableList<News>> {

    init {
        mData = ArrayList()
    }
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): MutableList<News> {
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