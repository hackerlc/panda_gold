package com.sk.panda.gold.ui.welcome

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sk.panda.gold.R
import com.sk.panda.gold.entity.News
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/16
 */
class NewsAdapter (mData: MutableList<News>) :
        BaseQuickAdapter<News, NewsAdapter.Holder>(R.layout.item_news, mData) {

    override fun convert(helper: Holder?, item: News) {
        if (helper != null) {
            helper.view.tv_title.text = item.title
        }
    }

    class Holder(var view: View): BaseViewHolder(view)
}