package com.sk.panda.gold.ui.commodity.list

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 商品列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface CommodityListContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}