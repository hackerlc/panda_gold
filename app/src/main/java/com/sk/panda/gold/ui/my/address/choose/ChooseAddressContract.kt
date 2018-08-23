package com.sk.panda.gold.ui.my.address.choose

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 选择收货地址
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface ChooseAddressContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}