package com.sk.panda.gold.ui.my.address.edit

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 收货地址编辑
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface AddressEditContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}