package com.sk.panda.gold.ui.gold

import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.extended.onHeadChange
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.head_common.*

/**
 * 金价趋势
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class GoldTrendActivity :
        BasePActivity<GoldTrendContract.Presenter<String>>(R.layout.activity_gold_trend),
        GoldTrendContract.View {

    override fun attachPresenter() {
        mPresenter = GoldTrendPresenter(this)
    }

    override fun initUI() {
        onHeadChange(head_top,tv_title,"金价走势",iv_left_img)

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