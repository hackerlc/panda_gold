package com.sk.panda.gold.base.activity

import com.sk.panda.gold.base.contract.BaseContract

/**
 * 给MVP结构的activity定义mPresenter变量类型，需要MVP的activity需要继承此类
 * @author joker
 * Email:812405389@qq.com
 * @version 2017/11/7
 */
abstract class BasePActivity<T : BaseContract.BasePresenter<*>>(viewId: Int) : BaseActivity(viewId){
    protected lateinit var mPresenter : T
}