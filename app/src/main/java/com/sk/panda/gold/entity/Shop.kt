package com.sk.panda.gold.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sk.panda.gold.extended.readMutableList

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/21
 */
class Shop() : Parcelable {
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("name")
    var name: String? = ""
    @SerializedName("freight")
    var freight: Int = 0
    @SerializedName("imgUrl")
    var imgUrl: String? = ""
    @SerializedName("goods")
    var goods: MutableList<Goods> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        imgUrl = parcel.readString()
        goods = parcel.readMutableList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(imgUrl)
        parcel.writeList(goods)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shop> {
        override fun createFromParcel(parcel: Parcel): Shop {
            return Shop(parcel)
        }

        override fun newArray(size: Int): Array<Shop?> {
            return arrayOfNulls(size)
        }
    }
}