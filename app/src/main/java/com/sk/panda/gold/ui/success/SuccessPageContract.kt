package com.sk.panda.gold.ui.success

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 操作成功页面
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface SuccessPageContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}