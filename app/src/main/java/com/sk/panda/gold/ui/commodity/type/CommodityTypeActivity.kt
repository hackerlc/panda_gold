package com.sk.panda.gold.ui.commodity.type

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.extended.onHeadChange
import com.sk.panda.gold.extended.setTabLayoutIndicatorWidth
import com.sk.panda.gold.ui.common.adapter.MyAdapter
import com.sk.panda.gold.ui.news.NewsFragment
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_commodity_type.*
import kotlinx.android.synthetic.main.head_common.*

/**
 * 商品分类
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityTypeActivity :
        BasePActivity<CommodityTypeContract.Presenter<String>>(R.layout.activity_commodity_type),
        CommodityTypeContract.View {
    companion object {
        fun startAct(context: Context){
                    context.startActivity(Intent(context,CommodityTypeActivity::class.java))
        }
    }

    private lateinit var adapter: MyAdapter
    private val titles = ArrayList<String>()
    val mFragment = ArrayList<Fragment>()

    override fun attachPresenter() {
        mPresenter = CommodityTypePresenter(this)
    }

    override fun initUI() {
        onHeadChange(head_top,tv_title,"商品分类",iv_left_img)

        titles.add("黄金")
        titles.add("项链")
        titles.add("戒指")
        titles.add("金币")
        titles.add("其他")

        for (i in 0 until titles.size){
            mFragment.add(NewsFragment())
        }
        adapter = MyAdapter(supportFragmentManager,titles,mFragment)
        vp_fm.adapter = adapter
        tl_tab.tabMode = TabLayout.MODE_SCROLLABLE
        tl_tab.post({
            tl_tab.setTabLayoutIndicatorWidth(25,25)
        })

        tl_tab.setupWithViewPager(vp_fm)


        onClickBind(this, iv_left_img)
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left_img -> toEnter(true)
        }
    }

    override fun updateUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this, str)
    }

    override fun onError(error: Throwable) {

    }

    override fun onDialog(show: Boolean) {
        if (show) {
            ProgressDialogUtil.getInstance().build(this).show()
        } else {
            ProgressDialogUtil.getInstance().dismiss()
        }
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }
}