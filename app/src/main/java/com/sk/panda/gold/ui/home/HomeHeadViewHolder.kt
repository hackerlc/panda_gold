package com.sk.panda.gold.ui.home

import android.graphics.Color
import android.view.View
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sk.panda.gold.entity.Banner
import com.sk.panda.gold.entity.HomeData
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.utils.GoldTodayYAxisValueFormatter
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.head_home_banner.view.*


/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/3/28
 */
class HomeHeadViewHolder(val view: View,d: MutableList<Banner>): View.OnClickListener {

    private var datas: MutableList<Banner> = d
    private val images: MutableList<String> = ArrayList()

    init {
        view.br_view.setDelayTime(5000)
        view.br_view.setIndicatorGravity(BannerConfig.CENTER)
        view.br_view.isAutoPlay(true)
//        view.br_view.setBannerAnimation(Transformer.ScaleInOut)
        view.br_view.setOnBannerListener({
            val banner = datas[it]
            //跳转
            if (banner.url.isNullOrEmpty()) {
//                WebActivity.startAct(view.context, banner.url!!, null)
            }
        })
        view.tv_message.setTextStillTime(3000)//设置停留时长间隔
        view.tv_message.setAnimTime(500)//设置进入和退出的时间间隔
        view.tv_message.startAutoScroll()
        initGold()
        onClickBind(this,view.ll_item_1,view.ll_item_2,view.ll_item_3,view.ll_item_4)
    }

    fun loadBanner(d: MutableList<Banner>){
        datas = d
        images.clear()
        for (data in datas) {
            images.add(data.img!!)
        }
        view.br_view.setImages(images)
                .setImageLoader(HomeBannerImageLoad())
                .start()
    }

    fun upHeadView(data: HomeData, strList: ArrayList<String>){
        view.tv_message.setTextList(strList)
        view.tv_message.setOnItemClickListener {
//            WebActivity.startAct(view.context,fetchHtmlUrl(HTML_URL_NEWS_DETAIL,0),"")
        }
//        view.tv_title.text = data.financial?.name
        //日息或者月息
//        val strShowTime = data.financial?.interestTypeBoStr
//        view.tv_date.text = "${data.financial?.time}$strShowTime"
//        view.tv_profit.text = "${CommonUtils.onFormat(data.financial?.yearProfit)}%"
        view.btn_buy.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
//                FinancialDetailActivity.startAct(view.context, data.financial?.id!!)
            }
        })

    }

    override fun onClick(v: View) {
        when(v.id){
//            R.id.ll_item_1 -> WebActivity.startAct(v.context,fetchHtmlUrl(HTML_URL_INFO_SHOW_A,0),"")
//            R.id.ll_item_2 -> WebActivity.startAct(v.context,fetchHtmlUrl(HTML_URL_SAFETY,0),"")
//            R.id.ll_item_3 -> WebActivity.startAct(v.context,fetchHtmlUrl(HTML_URL_ABOUT,0),"")
//            R.id.ll_item_4 -> WebActivity.startAct(v.context,fetchHtmlUrl(HTML_URL_ABOUT,0),"")
        }
    }

    private fun initGold(){
        //总设置
        //显示边界
        view.lc_gold.setDrawBorders(false)
        //不显示描述
        view.lc_gold.description.isEnabled = false
        //无数据显示文字
        view.lc_gold.setNoDataText("暂无数据")
        //是否显示表格颜色
        view.lc_gold.setDrawGridBackground(false)
        //是否可以缩放
        view.lc_gold.setScaleEnabled(false)
        //是否显示y轴右侧的值
        view.lc_gold.axisRight.isEnabled = false
        //不显示图例
        view.lc_gold.legend.isEnabled = false
//        val legend = view.lc_gold.legend
//        legend.isEnabled = false
        //x轴设置
        val xAxis = view.lc_gold.xAxis
        //不显示X轴
        xAxis.setDrawAxisLine(false)
        //不显示x轴线
        xAxis.setDrawGridLines(false)
        //x轴刻度
        xAxis.setLabelCount(5,false)
        xAxis.granularity = 300f
        //设置X轴显示位置
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //x轴设置显示
        xAxis.valueFormatter = GoldTodayYAxisValueFormatter()
        //x轴最大最小值显示
        xAxis.axisMinimum = 1534456800f
        xAxis.axisMaximum = 1534539600f

        //y轴设置
        val yAxis = view.lc_gold.axisLeft
        yAxis.setLabelCount(5,true)
        yAxis.setDrawAxisLine(false)
        yAxis.granularity = 1f


        //设置数据
        val entries = ArrayList<Entry>()
        entries.add(Entry(1534470872f,267.00f))
        entries.add(Entry(1534474472f,268.00f))
        entries.add(Entry(1534478072f,266.00f))
        entries.add(Entry(1534485272f,265.00f))
        entries.add(Entry(1534488872f,266.50f))
        entries.add(Entry(1534489872f,267.50f))
        entries.add(Entry(1534490872f,268.50f))

        //设置线
        //一个LineDataSet就是一条线
        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
        //设置线宽
        lineDataSet.lineWidth = 1.75f
        //设置线的颜色
        lineDataSet.color = Color.parseColor("#FFCF3E")
        //设置线的模式 平滑
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        //不显示小圆点
        lineDataSet.setDrawCircles(true)
        lineDataSet.setCircleColor(Color.parseColor("#FFCF3E"))
        lineDataSet.setDrawCircleHole(false)
        //不显示坐标点数据
        lineDataSet.setDrawValues(false)
        //设置是否填充
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.parseColor("#FFCF3E")


        val data = LineData(lineDataSet)
        view.lc_gold.data = data
        view.lc_gold.invalidate()
    }


    //黄金图表
    fun updateGold(datas: ArrayList<Entry>){
    }
}