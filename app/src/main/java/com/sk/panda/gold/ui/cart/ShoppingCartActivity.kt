package com.sk.panda.gold.ui.cart

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.entity.ShoppingGoods
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.extended.onHeadChange
import com.sk.panda.gold.ui.order.OrderVerifyActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.head_common.*

/**
 * 购物车
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class ShoppingCartActivity :
        BasePActivity<ShoppingCartContract.Presenter<ShoppingGoods>>(R.layout.activity_shopping_cart),
        ShoppingCartContract.View {

    lateinit var shoppingCartAdapter: ShoppingCartAdapter

    companion object {
        fun startAct(context: Context){
                    context.startActivity(Intent(context,ShoppingCartActivity::class.java))
        }
    }

    override fun attachPresenter() {
        mPresenter = ShoppingCartPresenter(this)
    }

    override fun initUI() {
        onHeadChange(head_top,tv_title,"购物车",iv_left_img,tv_right_text,"编辑全部")

        onClickBind(this,iv_left_img,tv_right_text,btn_ok)
    }

    override fun initData() {
        shoppingCartAdapter = ShoppingCartAdapter(this,mPresenter.getData().shop!!, LayoutInflater.from(this))
        elv_data.setGroupIndicator(null)
        elv_data.setAdapter(shoppingCartAdapter)

        mPresenter.fetch()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_ok -> {
                OrderVerifyActivity.startAct(this,mPresenter.getData())
            }
            R.id.iv_left_img -> {
                toEnter(true)
            }
        }
    }

    override fun updateUI() {
        shoppingCartAdapter.notifyDataSetChanged()
        for (i in 0 until mPresenter.getData().shop!!.size) {
            elv_data.expandGroup(i)
        }

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