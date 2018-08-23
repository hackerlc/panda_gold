package com.sk.panda.gold.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/21
 */
open class SKU() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        idSKU = parcel.readString()
        price = parcel.readValue(Double::class.java.classLoader) as Double
        spec = parcel.readString()
        size = parcel.readString()
        inventory = parcel.readInt()
        buyAmount = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idSKU)
        parcel.writeValue(price)
        parcel.writeString(spec)
        parcel.writeString(size)
        parcel.writeInt(inventory)
        parcel.writeInt(buyAmount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SKU> {
        override fun createFromParcel(parcel: Parcel): SKU {
            return SKU(parcel)
        }

        override fun newArray(size: Int): Array<SKU?> {
            return arrayOfNulls(size)
        }
    }
}