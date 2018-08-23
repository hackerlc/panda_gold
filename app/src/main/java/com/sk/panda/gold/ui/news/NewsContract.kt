package com.sk.panda.gold.ui.news

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 资讯列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface NewsContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}