package com.sk.panda.gold.ui.commodity.detail

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 标的详情
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface CommodityDetailContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}