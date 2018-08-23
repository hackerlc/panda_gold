package com.sk.panda.gold.ui.home

import com.sk.panda.gold.base.contract.BaseContract
import com.sk.panda.gold.entity.Banner

/**
 * 首页
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface HomeContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>{
        fun getBanner(): MutableList<Banner>
        fun getNews(): ArrayList<String>
    }
}