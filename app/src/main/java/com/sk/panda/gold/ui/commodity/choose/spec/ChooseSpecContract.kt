package com.sk.panda.gold.ui.commodity.choose.spec

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 选择规格
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface ChooseSpecContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}