package com.sk.panda.gold.ui.my.address.pickup

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 自提
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface PickUpContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}