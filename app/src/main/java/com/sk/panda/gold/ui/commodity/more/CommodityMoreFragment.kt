package com.sk.panda.gold.ui.commodity.more

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.fragment.BasePFragment
import com.sk.panda.gold.config.APIConfig
import com.sk.panda.gold.ui.common.adapter.MyAdapter
import com.sk.panda.gold.ui.web.WebH5Fragment
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil

@SuppressLint("ValidFragment")
/**
 * 标的详情更多信息
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityMoreFragment(var mId: String) :
        BasePFragment<CommodityMoreContract.Presenter<String>>(R.layout.fragment_commodity_more),
        CommodityMoreContract.View {

    val mTitle = ArrayList<String>()
    val mFragment = ArrayList<Fragment>()
    lateinit var mAdapter: MyAdapter

    override fun attachPresenter() {
        mPresenter = CommodityMorePresenter(this)
    }

    override fun initUI() {
        mTitle.add("安全保障")
        mTitle.add("项目信息")
        mTitle.add("投资记录")
        mFragment.add(WebH5Fragment(APIConfig.fetchHtmlUrl(APIConfig.HTML_URL_PRODUCT_SAFETY, 0, mId), "", 0, true))
        mFragment.add(WebH5Fragment(APIConfig.fetchHtmlUrl(APIConfig.HTML_URL_PRODUCT_DETAIL, 0, mId), "", 0, true))
        mFragment.add(WebH5Fragment(APIConfig.fetchHtmlUrl(APIConfig.HTML_URL_PRODUCT_RECORD, 0, mId), "", 0, true))

        mAdapter = MyAdapter(childFragmentManager, mTitle, mFragment)
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