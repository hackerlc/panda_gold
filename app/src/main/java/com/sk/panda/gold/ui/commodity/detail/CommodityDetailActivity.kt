package com.sk.panda.gold.ui.commodity.detail

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sk.panda.gold.ui.commodity.more.CommodityMoreFragment
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.config.AppConfig
import com.sk.panda.gold.entity.Commodity
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.extended.onHeadChange
import com.sk.panda.gold.ui.commodity.choose.spec.ChooseSpecActivity
import com.sk.panda.gold.ui.commodity.info.CommodityInfoFragment
import com.sk.panda.gold.ui.common.dialog.CommonDialog
import com.sk.panda.gold.ui.login.LoginActivity
import com.sk.panda.gold.ui.main.MainActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_commodity_detail.*
import kotlinx.android.synthetic.main.head_common.*


/**
 * 商品详情
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityDetailActivity :
        BasePActivity<CommodityDetailContract.Presenter<Commodity>>(R.layout.activity_commodity_detail),
        CommodityDetailContract.View, CommonDialog.onDialogDoubleButtonClickListener {

    lateinit var mInfo: CommodityInfoFragment
    lateinit var mMore: CommodityMoreFragment
    var id :String? = null
//
//    /**
//     * 开户dialog
//     */
//    lateinit var dialog: CommonDialog
//    /**
//     * 跳转列表页
//     */
//    lateinit var dialogList: CommonDialog

    companion object {
        fun startAct(context: Context,id: String?){
                    context.startActivity(Intent(context, CommodityDetailActivity::class.java)
                            .putExtra("id",id))
        }
    }

    override fun attachPresenter() {
        id = intent.getStringExtra("id")
        if (id.isNullOrEmpty()){
            ToastUtil.getInstance().makeShortToast(this,"暂无标的详情")
            toExit(true)
        }
        mPresenter = CommodityDetailPresenter(this,id!!)
    }

    override fun initUI() {
        onHeadChange(head_top,tv_title,"标的详情",iv_left_img)
//
//        dialog = CommonDialog(this,"","为了保障您的资金安全\n在您投资前需要开通 汇付天下托管账户"
//                ,0,"稍后开通","立即开通",this)
//        dialogList = CommonDialog(this,"","该产品已满额，请选择其他产品出借"
//                ,1,"确定","",object : CommonDialog.onDialogDoubleButtonClickListener{
//            override fun onLeftClick() {
//                dialogList.dismiss()
//                MainActivity.startAct(this@CommodityDetailActivity,1)
//            }
//
//            override fun onRightClick() {
//            }
//
//        })
    }

    override fun initData() {
        mInfo = CommodityInfoFragment(mPresenter.getData())
        mMore = CommodityMoreFragment(id!!)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.rl_top_fm, mInfo)
        ft.add(R.id.rl_bottom_fm, mMore)
        ft.commit()
        setOnGoodsDetailsListener(object : OnGoodsDetailsListener {
            override fun onShow(showDetails: Boolean) {
                if (showDetails){
                    tv_title.text = "项目信息"
                } else {
                    tv_title.text = "标的详情"
                }
            }
        })
        initPullDown()
        initPullUp()
        onClickBind(this,iv_left_img,btn_ok)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.fetch()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left_img -> {
                toExit(true)
            }
            R.id.btn_ok -> {
                ChooseSpecActivity.startAct(this)
//                if (AppConfig.IS_LOGIN) {
//
//                } else {
//                    LoginActivity.startAct(this)
//                }
            }
        }
    }

    override fun onLeftClick() {
    }

    override fun onRightClick() {
    }

    override fun updateUI() {
        //判断标的是否为已完结修改按钮不可点击

//        mInfo.notifyDataSetChanged(mPresenter.getData())
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this, str)
    }

    //上拉部分
    private var rect: Rect? = null//记录scrollView包裹组件的位置
    private var fullScroll: Int = 0//scrollView滚动到底部时ScrollView的scrollY值
    private var mDeltaY: Float = 0.toFloat()//（上拉模块）拖动的距离
    private var downScrollY: Int = 0//开始拖动的ScrollView的scrollY值
    private var startY: Float = 0.toFloat()//开始拖动的MotionEvent的y值

    //下拉部分
    private var startY2: Float = 0.toFloat()
    private var downScrollY2: Float = 0.toFloat()
    private var mDeltaY2: Float = 0.toFloat()
    private var touched: Boolean = false//是否触摸，在ACTION_MOVE中做标记，记录按下时需要的值
    private var mLayoutParams: RelativeLayout.LayoutParams? = null
    /*******************监听详情的隐藏和显示 */

    private var mOnGoodsDetailsListener: OnGoodsDetailsListener? = null

    fun setOnGoodsDetailsListener(onGoodsDetailsListener: OnGoodsDetailsListener) {
        mOnGoodsDetailsListener = onGoodsDetailsListener
    }

    interface OnGoodsDetailsListener {
        fun onShow(showDetails: Boolean)
    }
    //下拉隐藏详情
    private fun initPullDown() {
        mLayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mLayoutParams?.setMargins(0, resources.getDimension(R.dimen.pulldown_head_margin).toInt(), 0, 0)
        llDownScroll.layoutParams = mLayoutParams
        sv_bottom.setOnTouchListener({ _, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                }

                MotionEvent.ACTION_UP -> {

                    //向下拖动距离大于100
                    if (mDeltaY2 > 100 && downScrollY2 == 0f) {

                        //显示上部
                        sv_top.visibility = View.VISIBLE
                        sv_top.smoothScrollTo(0, fullScroll)//滚动到底部
                        //隐藏详情
                        sv_bottom.visibility = View.GONE

                        //还原标题-->商品 详情 评价
                        //修改返回按键和小箭头的事件->点击结束act
                        //将状态传到activity，改变标题
                        mOnGoodsDetailsListener?.onShow(false)
                    }

                    //                      //恢复原有marginTop高度，隐藏头
                    mLayoutParams?.setMargins(0, resources.getDimension(R.dimen.pulldown_head_margin).toInt(), 0, 0)
                    llDownScroll.layoutParams = mLayoutParams

                    //重置
                    mDeltaY2 = 0f
                    downScrollY2 = 0f
                    startY2 = 0f
                    touched = false
                }
                MotionEvent.ACTION_MOVE -> {
                    //从顶部开始的滑动，且向下滑

                    //在此记录按下位置，取代ACTION_DOWN中
                    if (!touched) {
                        startY2 = event.y
                        downScrollY2 = sv_bottom.scrollY.toFloat()
                    }
                    touched = true
                    mDeltaY2 = 0.5f * (event.y - startY2)

                    if (downScrollY2 == 0f && mDeltaY2 > 0) {
                        //计算marginTop高度，动态显示头高度
                        val top = mDeltaY2.toInt()+resources.getDimension(R.dimen.pulldown_head_margin).toInt()
                        mLayoutParams?.setMargins(0, top, 0, 0)
                        llDownScroll.layoutParams = mLayoutParams
                    }
                }
                else -> {
                }
            }//可能的冲突，改为在ACTION_MOVE中获取
            return@setOnTouchListener false
        })
    }


    /**
     * 上拉查看详情
     */
    private fun initPullUp() {

        //这一步操作为，获取ScrollView的完全高度，在上拉时，判断是否从最底部开始
        sv_top.setOnScrollToBottomLintener({ isBottom ->
            if (isBottom) {
                fullScroll = sv_top.scrollY
            }
        })

        sv_top.setOnTouchListener(View.OnTouchListener { _, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    //记录按下时的Y值
                    startY = event.y.toInt().toFloat()
                    downScrollY = sv_top.scrollY
                    if (rect == null) {
                        rect = Rect(scrollContainer.left, scrollContainer.top, scrollContainer.right, scrollContainer.bottom)
                    }
                }

                MotionEvent.ACTION_UP -> {

                    //拖动距离小于
                    if (mDeltaY < -170) {
                        //显示详情
                        sv_bottom.visibility = View.VISIBLE
                        sv_bottom.smoothScrollTo(0, 0)
                        //隐藏上部
                        sv_top.visibility = View.GONE

                        //改变标题-->图文详情
                        //修改返回按键和小箭头的事件->点击还原
                        //将状态传到activity，改变标题
                        mOnGoodsDetailsListener?.onShow(true)
                    }

                    // 恢复原有高度
                    if (rect != null) {
                        scrollContainer.layout(rect!!.left, rect!!.top, rect!!.right, rect!!.bottom)
                    }
                    //重置
                    mDeltaY = 0f
                    downScrollY = 0
                    startY = 0f
                }
                MotionEvent.ACTION_MOVE ->
                    //downScrollY != 0：不是从顶部开始的滑动；fullScroll不为0且不为负值(bug?)
                    if (downScrollY !== 0 && fullScroll > 0 && downScrollY >= fullScroll - 20) {
                        //deltaY<0，向上滑动
                        mDeltaY = 0.5f * (event.y - startY)
                        if (rect != null) {
                            scrollContainer.layout(rect!!.left, (rect!!.top + mDeltaY.toInt()), rect!!.right, (rect!!.bottom + mDeltaY.toInt()))
                        }
                    }
                else -> {
                }
            }
            return@OnTouchListener false
        })

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