package com.sk.panda.gold.ui.order

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.entity.ShoppingGoods
import com.sk.panda.gold.ui.order.adapter.OrderVerfyAdapter
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.ui.custom.GearRecyclerItemDecoration
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_order_verify.*

/**
 * 订单确认
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class OrderVerifyActivity :
        BasePActivity<OrderVerifyContract.Presenter<ShoppingGoods>>(R.layout.activity_order_verify),
        OrderVerifyContract.View {

    lateinit var mGoods: ShoppingGoods

    lateinit var orderAdapter: OrderVerfyAdapter

    companion object {
        val PUT_EXTRA_GOODS = "goods"
        fun startAct(context: Context, mGoods: ShoppingGoods){
                    context.startActivity(Intent(context,OrderVerifyActivity::class.java)
                            .putExtra(PUT_EXTRA_GOODS,mGoods))
        }
    }

    override fun attachPresenter() {
        mPresenter = OrderVerifyPresenter(this)
    }

    override fun initUI() {
        mGoods = intent.getParcelableExtra(PUT_EXTRA_GOODS)
        if (mGoods.shop.size <=0){
            showToast("请选择商品")
            toEnter(true)
        }

        orderAdapter = OrderVerfyAdapter(mGoods.shop)
        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.addItemDecoration(GearRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,1))

    }

    override fun initData() {
        rv_data.adapter = orderAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {

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