package com.sk.panda.gold.ui.my.phone.psd

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 使用密码方式修改手机号
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
interface ChangePsdPhoneContract {
    interface View : BaseContract.BaseView{
        fun onCheckSuccess()
    }

    interface Presenter<T> : BaseContract.BasePresenter<T>{
        fun onCheckPhone(tel: String, psd: String, code: String)
    }
}