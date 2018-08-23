package com.sk.panda.gold.ui.commodity.type

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 商品分类
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface CommodityTypeContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}