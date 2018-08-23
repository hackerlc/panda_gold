package com.sk.panda.gold.ui.commodity.info

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar
import com.sk.panda.gold.R
import com.sk.panda.gold.base.fragment.BasePFragment
import com.sk.panda.gold.entity.Commodity
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil


@SuppressLint("ValidFragment")
/**
 * 标的详情信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityInfoFragment(var mFinancial: Commodity) :
        BasePFragment<CommodityInfoContract.Presenter<Commodity>>(R.layout.fragment_commodity_info),
        CommodityInfoContract.View {

    override fun attachPresenter() {
        mPresenter = CommodityInfoPresenter(this)
    }

    override fun initUI() {
    }

    override fun initData() {
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    fun notifyDataSetChanged(commodity: Commodity){
        mFinancial = commodity
        updateUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun updateUI() {
    }

    private fun setAnimation(view: ProgressBar, mProgressBar: Int) {
        val animator = ValueAnimator.ofInt(0, mProgressBar).setDuration(3000)

        animator.addUpdateListener { valueAnimator -> view.progress = valueAnimator.animatedValue as Int }
        animator.start()
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(context, str)
    }

    override fun onError(error: Throwable) {

    }

    override fun onDialog(show: Boolean) {
        if (show) {
            ProgressDialogUtil.getInstance().build(context!!).show()
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