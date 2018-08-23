package com.sk.panda.gold.ui.my.phone.enter

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 确认新手机号
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface EnterNewPhoneContract {
    interface View : BaseContract.BaseView{
        fun ChangeSuccess()
    }

    interface Presenter<T> : BaseContract.BasePresenter<T>{
        fun fetchCode(tel: String)
        fun onChangePhone(tel: String,code: String)
    }
}