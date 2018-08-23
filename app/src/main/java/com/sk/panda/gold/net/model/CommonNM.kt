package com.sk.panda.gold.net.model

import com.sk.panda.gold.base.net.BaseNM
import com.sk.panda.gold.entity.HomeData
import com.sk.panda.gold.net.api.ApiManager
import com.sk.panda.gold.net.helper.RxNetWorkHelper
import com.sk.panda.gold.entity.ResponseJson
import com.sk.panda.gold.entity.UpApp
import io.reactivex.Flowable

/**
 * 标的列表 info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/4/4
 */
class CommonNM: BaseNM() {

    /**
     * 获取首页数据
     */
    fun fetchHome(): Flowable<ResponseJson<HomeData>> {
        mRP.map.clear()
        return ApiManager.COMMON_API.fetchHome(mRP.map)
                .compose(RxNetWorkHelper.isNetWork())
    }/**
     * 更新app
     */
    fun upDataApp(): Flowable<ResponseJson<UpApp>> {
        mRP.map.clear()
        mRP.map["type"] = "android"
        return ApiManager.COMMON_API.updateApp(mRP.map)
                .compose(RxNetWorkHelper.isNetWork())
    }
}