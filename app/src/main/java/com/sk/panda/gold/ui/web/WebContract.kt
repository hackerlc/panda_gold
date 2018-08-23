package com.sk.panda.gold.ui.web

import com.sk.panda.gold.base.contract.BaseContract
import com.sk.panda.gold.entity.UpApp

/**
 * 查看网页
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface WebContract {
    interface View : BaseContract.BaseView{
        fun upDataApp(data: UpApp)
    }

    interface Presenter<T> : BaseContract.BasePresenter<T>{
        fun upDataApp()
    }
}