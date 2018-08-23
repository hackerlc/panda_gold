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
class Goods() : Parcelable {
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("name")
    var name: String? = ""
    @SerializedName("showPrice")
    var showPrice: String? = ""
    @SerializedName("sku_id")
    var idSKU: String? = ""
    @SerializedName("price")
    var price: Double = 0.0
    @SerializedName("spec")
    var spec: String? = ""
    @SerializedName("size")
    var size: String? = ""
    @SerializedName("inventory")
    var inventory: Int = 0
    @SerializedName("buyAmount")
    var buyAmount: Int = 0
    @SerializedName("images")
    var images: MutableList<String> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        showPrice = parcel.readString()
        idSKU = parcel.readString()
        price = parcel.readDouble()
        spec = parcel.readString()
        size = parcel.readString()
        inventory = parcel.readInt()
        buyAmount = parcel.readInt()
        images = parcel.readMutableList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(showPrice)
        parcel.writeString(idSKU)
        parcel.writeDouble(price)
        parcel.writeString(spec)
        parcel.writeString(size)
        parcel.writeInt(inventory)
        parcel.writeInt(buyAmount)
        parcel.writeList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Goods> {
        override fun createFromParcel(parcel: Parcel): Goods {
            return Goods(parcel)
        }

        override fun newArray(size: Int): Array<Goods?> {
            return arrayOfNulls(size)
        }
    }

}