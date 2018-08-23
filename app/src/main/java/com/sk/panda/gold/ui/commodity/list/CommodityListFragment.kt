package com.sk.panda.gold.ui.commodity.list

import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.fragment.BasePFragment
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil

/**
 * 商品列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityListFragment :
        BasePFragment<CommodityListContract.Presenter<String>>(R.layout.fragment_commodity_list),
        CommodityListContract.View {

    override fun attachPresenter() {
        mPresenter = CommodityListPresenter(this)
    }

    override fun initUI() {

    }

    override fun initData() {
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun updateUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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