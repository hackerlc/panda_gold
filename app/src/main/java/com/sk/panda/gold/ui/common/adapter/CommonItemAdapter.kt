package com.sk.panda.gold.ui.common.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sk.panda.gold.R
import com.sk.panda.gold.base.glide.AGlide
import com.sk.panda.gold.entity.Commodity
import kotlinx.android.synthetic.main.item_common_adapter.view.*

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/3/28
 */
class CommonItemAdapter(mData: MutableList<Commodity>) :
        BaseQuickAdapter<Commodity, CommonItemAdapter.Holder>(R.layout.item_common_adapter, mData) {

    override fun convert(helper: Holder?, item: Commodity) {
        if (helper != null) {
            helper.view.tv_name.text = item.name
            helper.view.tv_price.text = item.price

            AGlide.with(mContext).asBitmap().load(item.img).into(helper.view.iv_img)
        }
    }

    fun upData(data: MutableList<Commodity>){
        mData = data
        notifyDataSetChanged()
    }

    class Holder(var view: View): BaseViewHolder(view)
}