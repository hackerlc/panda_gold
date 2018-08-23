package com.sk.panda.gold.ui.order.adapter

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sk.panda.gold.R
import com.sk.panda.gold.entity.Goods
import com.sk.panda.gold.entity.Shop
import kotlinx.android.synthetic.main.item_order_goods.view.*
import kotlinx.android.synthetic.main.item_order_verify.view.*

/**
 * 投资信息
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/3/28
 */
class OrderVerfyGoodsAdapter(mData: MutableList<Goods>) :
        BaseQuickAdapter<Goods, OrderVerfyGoodsAdapter.Holder>(R.layout.item_order_goods, mData) {



    override fun convert(helper: Holder?, item: Goods) {
        if (helper != null) {
            helper.view.tv_name.text = item.name
        }
    }

    class Holder(var view: View): BaseViewHolder(view)
}