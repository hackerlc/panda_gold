package com.sk.panda.gold.entity

import com.google.gson.annotations.SerializedName

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/15
 */
class Commodity {
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("fileName")
    var name: String? = ""
    @SerializedName("price")
    var price: String? = ""
    @SerializedName("filePath")
    var img: String? = ""

}