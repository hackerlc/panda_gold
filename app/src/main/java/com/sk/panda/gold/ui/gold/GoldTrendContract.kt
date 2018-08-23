package com.sk.panda.gold.ui.gold

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 金价趋势
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface GoldTrendContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}