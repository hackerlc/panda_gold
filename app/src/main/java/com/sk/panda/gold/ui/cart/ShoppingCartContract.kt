package com.sk.panda.gold.ui.cart

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 购物车
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface ShoppingCartContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}