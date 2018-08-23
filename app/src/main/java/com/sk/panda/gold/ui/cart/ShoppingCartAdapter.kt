package com.sk.panda.gold.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.sk.panda.gold.R
import com.sk.panda.gold.entity.Shop
import kotlinx.android.synthetic.main.item_group_cart.view.*
import kotlinx.android.synthetic.main.item_child_cart.view.*


/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/21
 */
class ShoppingCartAdapter(val context: Context, val mData: MutableList<Shop>, val inflater: LayoutInflater)
    : BaseExpandableListAdapter() {

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view: View
        var groupHolder: GroupViewHolder
        if (convertView == null){
            view = inflater.inflate(R.layout.item_group_cart, null)
            groupHolder = GroupViewHolder()
            groupHolder.view = view
            view.tag = groupHolder
        } else {
            view = convertView
            groupHolder = view.tag as GroupViewHolder
        }
        groupHolder.view!!.tv_shop_name.text = mData[groupPosition].name

        return view
    }
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var mChildData = mData[groupPosition].goods[childPosition]

        var view: View
        var childHolder: ChildViewHolder
        if (convertView == null){
            view = inflater.inflate(R.layout.item_child_cart, null)
            childHolder = ChildViewHolder()
            childHolder.viewChild = view
            view.tag = childHolder
        } else {
            view = convertView
            childHolder = view.tag as ChildViewHolder
        }
        childHolder.viewChild!!.tv_name.text = mChildData.name
        childHolder.viewChild!!.tv_price.text = mChildData.price.toString()
        childHolder.viewChild!!.av_amount.initAmount(mChildData.buyAmount)
        childHolder.viewChild!!.av_amount.setGoods_storage(mChildData.inventory)

        return view
    }

    override fun getGroup(groupPosition: Int): Any {
        return mData[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return mData[groupPosition].goods.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mData[groupPosition].goods[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }



    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return mData.size
    }

    class GroupViewHolder{
        var view: View? = null
    }

    class ChildViewHolder{
        var viewChild: View? = null
    }

}