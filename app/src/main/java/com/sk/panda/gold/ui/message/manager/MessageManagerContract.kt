package com.sk.panda.gold.ui.message.manager

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 消息中心
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface MessageManagerContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}