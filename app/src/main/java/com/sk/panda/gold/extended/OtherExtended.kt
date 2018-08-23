package com.sk.panda.gold.extended

import android.os.Parcel
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.sk.panda.gold.ui.home.HomeHeadViewHolder

/**
 * 其他类扩展
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/4/28
 */
/**
 * activity 控件绑定
 * @param click 点击事件
 * @param views 控件集合
 */
inline fun HomeHeadViewHolder.onClickBind(click: View.OnClickListener, vararg views: View) {
    for(view in views){
        view.setOnClickListener(click)
    }
}

/**
 * 图表数据扩展
 * */
fun LineChart.xValuesProcess(): Array<String>{
    val today = arrayOf("6:00", "11:45", "17:30", "23:15", "5:00")
    return today
}

/**
 *
 */
inline fun <reified T> Parcel.readMutableList(): MutableList<T> {
    @Suppress("UNCHECKED_CAST")
    return readArrayList(T::class.java.classLoader) as MutableList<T>
}