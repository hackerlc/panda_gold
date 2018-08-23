package com.sk.panda.gold.ui.my.phone.code

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 修改原手机号
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface ChangeOldPhoneContract {
    interface View : BaseContract.BaseView{
        fun checkSuccess()
    }

    interface Presenter<T> : BaseContract.BasePresenter<T>{
        fun fetchCode(tel: String)
        fun onChange(tel: String,psd: String,code: String)
    }
}