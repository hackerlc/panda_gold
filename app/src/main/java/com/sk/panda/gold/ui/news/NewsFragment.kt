package com.sk.panda.gold.ui.news

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sk.panda.gold.R
import com.sk.panda.gold.base.fragment.BasePFragment
import com.sk.panda.gold.entity.News
import com.sk.panda.gold.extended.onDefaultSmartRefreshLayout
import com.sk.panda.gold.ui.welcome.NewsAdapter
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.ui.custom.GearRecyclerItemDecoration
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_layout.*

/**
 * 资讯列表
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class NewsFragment :
        BasePFragment<NewsContract.Presenter<MutableList<News>>>(R.layout.fragment_layout),
        NewsContract.View {
    lateinit var mAdapter: NewsAdapter

    override fun attachPresenter() {
        mPresenter = NewsPresenter(this)
    }

    override fun initUI() {
        rv_view.layoutManager = LinearLayoutManager(context)
        rv_view.addItemDecoration(GearRecyclerItemDecoration(context,LinearLayoutManager.VERTICAL,10))

        sr_layout.onDefaultSmartRefreshLayout()
        sr_layout.setOnRefreshListener{
            mPresenter.refreshData()
        }
        sr_layout.setOnLoadmoreListener{
            mPresenter.refreshData()
        }
    }

    override fun initData() {
        mAdapter = NewsAdapter(mPresenter.getData())
        rv_view.adapter = mAdapter
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->
        }
        mPresenter.fetch()
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