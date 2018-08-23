package com.sk.panda.gold.ui.main

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 主要承载页面
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface MainContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}