package com.sk.panda.gold.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sk.panda.gold.extended.readMutableList

/**
 * 购物车
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/21
 */
class ShoppingGoods() : Parcelable {
    @SerializedName("shop")
    var shop: MutableList<Shop> = ArrayList()

    constructor(parcel: Parcel) : this() {
        this.shop = parcel.readMutableList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(shop)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShoppingGoods> {
        override fun createFromParcel(parcel: Parcel): ShoppingGoods {
            return ShoppingGoods(parcel)
        }

        override fun newArray(size: Int): Array<ShoppingGoods?> {
            return arrayOfNulls(size)
        }
    }
}