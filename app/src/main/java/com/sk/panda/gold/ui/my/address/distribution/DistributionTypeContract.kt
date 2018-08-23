package com.sk.panda.gold.ui.my.address.distribution

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 配送方式
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface DistributionTypeContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}