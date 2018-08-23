package com.sk.panda.gold.net.api

import com.sk.panda.gold.entity.*
import io.reactivex.Flowable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 提供接口调用方法
 * @author joker
 * Email:812405389@qq.com
 * @version 2017/11/8
 */
interface CommonApi {
    /**
     * 首页数据
     */
    @FormUrlEncoded
    @POST("index")
    fun fetchHome(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<HomeData>>
    /**
     * app更新
     */
    @FormUrlEncoded
    @POST("updateapp")
    fun updateApp(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<UpApp>>
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("login/login")
    fun onLogin(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<User>>
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("login/userRegister")
    fun onRegister(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<User>>
    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("users/changePwd")
    fun onChangePassword(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("login/foundPwd")
    fun onFoundPassword(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 验证手机号
     */
    @FormUrlEncoded
    @POST("login/changePhoneCheck")
    fun onCheckPhone(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 修改手机号
     */
    @FormUrlEncoded
    @POST("login/changePhone")
    fun onChangePhone(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("sms/sendMessage")
    fun fetchCode(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 绑定银行卡
     */
    @FormUrlEncoded
    @POST("users/bindCard")
    fun bindingBankCard(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 解绑银行卡
     */
    @FormUrlEncoded
    @POST("users/delCard")
    fun unBindingBankCard(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<String>>
    /**
     * 解绑银行卡
     */
    @FormUrlEncoded
    @POST("users/queryBankCardInfo")
    fun fetchBankCard(@FieldMap map: MutableMap<String, Any>): Flowable<ResponseJson<MutableList<UserBank>>>
}