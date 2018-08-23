package com.sk.panda.gold.entity

import com.google.gson.annotations.SerializedName

/**
 * 首页数据
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/4/20
 */
class HomeData {

    /**
     * 轮播图
     */
    @SerializedName("broadcastImg")
    var banners: MutableList<Banner> = ArrayList()

    /**
     * 平台公告
     */
    @SerializedName("flashInfo")
    var news: MutableList<News> = ArrayList()
    /**
     * 商品列表
     */
    @SerializedName("data")
    var commodity: MutableList<Commodity> = ArrayList()
}