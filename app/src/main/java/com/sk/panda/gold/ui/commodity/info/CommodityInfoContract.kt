package com.sk.panda.gold.ui.commodity.info

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 标的详情信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface CommodityInfoContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}