package com.sk.panda.gold.ui.order

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 订单确认
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface OrderVerifyContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}