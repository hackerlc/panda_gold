package com.sk.panda.gold.ui.message.manager

import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.extended.onHeadChange
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_message_manager.*
import kotlinx.android.synthetic.main.head_common.*

/**
 * 消息中心
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class MessageManagerActivity :
        BasePActivity<MessageManagerContract.Presenter<String>>(R.layout.activity_message_manager),
        MessageManagerContract.View {

    override fun attachPresenter() {
        mPresenter = MessageManagerPresenter(this)
    }

    override fun initUI() {
        onHeadChange(head_top,tv_title,"消息中心",iv_left_img)

        onClickBind(this,iv_left_img, rl_message, rl_online)
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