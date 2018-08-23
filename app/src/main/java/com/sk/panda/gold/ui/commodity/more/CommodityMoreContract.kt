package com.sk.panda.gold.ui.commodity.more

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 标的详情更多信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface CommodityMoreContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}