package com.sk.panda.gold.ui.order.adapter

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sk.panda.gold.R
import com.sk.panda.gold.entity.Shop
import kotlinx.android.synthetic.main.item_order_verify.view.*

/**
 * 投资信息
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/3/28
 */
class OrderVerfyAdapter(mData: MutableList<Shop>) :
        BaseQuickAdapter<Shop, OrderVerfyAdapter.Holder>(R.layout.item_order_verify, mData) {

    lateinit var goodsAdapter: OrderVerfyGoodsAdapter

    override fun convert(helper: Holder?, item: Shop) {
        if (helper != null) {
            helper.view.tv_shop_name.text = item.name
            helper.view.tv_express.text = item.freight.toString()
            var price = 0.0
            for (goods in item.goods){
                price += goods.price * goods.buyAmount
            }
            helper.view.tv_price.text = price.toString()
            helper.view.tv_total_price.text = (price + item.freight).toString()

            //adapter
            helper.view.rv_goods.layoutManager = LinearLayoutManager(mContext)

            goodsAdapter = OrderVerfyGoodsAdapter(item.goods)
            helper.view.rv_goods.adapter = goodsAdapter
        }
    }

    class Holder(var view: View): BaseViewHolder(view)
}