package com.sk.panda.gold.ui.home

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.Banner
import com.sk.panda.gold.entity.Commodity
import com.sk.panda.gold.entity.HomeData
import com.sk.panda.gold.net.helper.SchedulersDataHelper
import com.sk.panda.gold.net.model.CommonNM
import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper

/**
 * 首页
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class HomePresenter(v: HomeContract.View) :
        PresenterDataWrapper<HomeData, HomeContract.View>(v),
        HomeContract.Presenter<HomeData> {

    private val mNM = CommonNM()
    init {
        mData = HomeData()
    }
    override fun fetch() {
        mNM.fetchHome()
                .compose(mView.get()!!.getLifecycleDestroy())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersDataHelper.handleResult())
                .subscribe({ processData(it) },
                            { errorData(it) })

    }

    override fun processData(d: HomeData) {
        super.processData(d)
        mData.banners.clear()
        mData.banners.addAll(d.banners)
//        mData.financial = d.financial
        mData.news.clear()
        mData.news.addAll(d.news)
        mData.commodity.clear()

        var cdity = Commodity()
        cdity.name = "你好"
        cdity.price = "￥1200.00"
        cdity.img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534501241200&di=2b203f452c8cf4edea13af1e69e1af77&imgtype=0&src=http%3A%2F%2Fs8.sinaimg.cn%2Fmiddle%2F8ee3e0acxb0171b491f27%26690"
        mData.commodity.add(cdity)
        mData.commodity.add(cdity)
        mData.commodity.add(cdity)
        mData.commodity.add(cdity)
        mData.commodity.add(cdity)

        mView.get()?.updateUI()
    }

    override fun refreshData() {
        fetch()
    }

    override fun getData(): HomeData {
        return mData
    }

    override fun getBanner(): MutableList<Banner> {
        return mData.banners
    }

    override fun getNews(): ArrayList<String> {
        var newsList = ArrayList<String>()
        for (news in mData.news){
            newsList.add(news.title!!)
        }
        return newsList
    }

    override fun errorData(error: Throwable) {
        super.errorData(error)
        mView.get()?.onError(error)
    }

    override fun close() {
        mView.clear()
    }
}