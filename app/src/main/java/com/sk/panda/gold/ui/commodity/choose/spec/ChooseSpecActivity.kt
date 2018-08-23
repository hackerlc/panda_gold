package com.sk.panda.gold.ui.commodity.choose.spec

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_chooesgood_specification.*


/**
 * 选择规格
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class ChooseSpecActivity :
        BasePActivity<ChooseSpecContract.Presenter<String>>(R.layout.activity_chooesgood_specification),
        ChooseSpecContract.View {
    companion object {
        fun startAct(context: Context){
                    context.startActivity(Intent(context,ChooseSpecActivity::class.java))
        }
    }

    override fun attachPresenter() {
        mPresenter = ChooseSpecPresenter(this)
    }

    override fun initUI() {
        val mVals = ArrayList<String>()
        mVals.add("20g")
        mVals.add("30g")
        mVals.add("40g")
        mVals.add("50g")
        mVals.add("60g")
        mVals.add("100g")
        mVals.add("10000g")
        mVals.add("1000g")
        tf_spec.adapter = object : TagAdapter<String>(mVals){
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = LayoutInflater.from(this@ChooseSpecActivity).inflate(R.layout.item_spec,
                        tf_spec, false) as TextView
                tv.text = t
                return tv
            }
        }
        tf_spec.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener{
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                showToast( mVals[position])
                return true
            }
        })

        val mValss = ArrayList<String>()
        mValss.add("13.5cm")
        mValss.add("12cm")
        mValss.add("15cm")
        mValss.add("18.6cm")
        mValss.add("20cm")
        tf_size.adapter = object : TagAdapter<String>(mValss){
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = LayoutInflater.from(this@ChooseSpecActivity).inflate(R.layout.item_spec,
                        tf_size, false) as TextView
                tv.text = t
                return tv
            }
        }
        tf_size.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener{
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                showToast( mValss[position])
                return true
            }
        })

    }

    override fun initData() {
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