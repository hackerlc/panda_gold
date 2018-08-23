package com.sk.panda.gold.ui.message.platform

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 平台通知
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface PlatformMessageContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}