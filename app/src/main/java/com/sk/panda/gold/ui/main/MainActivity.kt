package com.sk.panda.gold.ui.main

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import com.sk.panda.gold.R
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.ui.cart.ShoppingCartActivity
import com.sk.panda.gold.ui.commodity.detail.CommodityDetailActivity
import com.sk.panda.gold.ui.commodity.type.CommodityTypeActivity
import com.sk.panda.gold.ui.news.NewsFragment
import com.sk.panda.gold.ui.home.HomeFragment
import com.sk.panda.gold.ui.login.lock.GestureCreateActivity
import com.sk.panda.gold.ui.my.address.edit.AddressEditActivity
import com.sk.panda.gold.ui.my.address.pickup.PickUpActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 主要承载页面
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class MainActivity :
        BasePActivity<MainContract.Presenter<String>>(R.layout.activity_main),
        MainContract.View {

    private var mSelectedFragmentId = -1
    private val fragments = HashMap<Int, Fragment>()

    /**
     * 其他页面跳转过来显示页面
     */
    private var mEnter = 0

    companion object {
        /**
         * @param enter 0 首页 1 随心购 2 资讯 3 我的
         */
        fun startAct(context: Context, enter: Int) {
            context.startActivity(Intent(context, MainActivity::class.java)
                    .putExtra("enter", enter))
        }
    }

    override fun attachPresenter() {
        mPresenter = MainPresenter(this)
    }

    override fun initUI() {
        onClickBind(this, tab_home, tab_gold, tab_my,tab_news)
    }

    override fun initData() {
        initFragment()
    }

    private var calEnter = -1
    override fun onNewIntent(intent: Intent?) {
        setIntent(intent)
        calEnter = intent?.getIntExtra("enter",-1)!!
    }

    override fun onRestart() {
        super.onRestart()
        if (mEnter != calEnter){
            mEnter =calEnter
            if (mEnter in 0..3) {
                when (mEnter) {
                    0 -> {
                        onChangeFragment(R.id.tab_home)
                        tab_home.isSelected = true
                    }
                    1 -> {
                        onChangeFragment(R.id.tab_gold)
                        tab_gold.isSelected = true
                    }
                    2 -> {

                    }
                    3 -> {
                        onChangeFragment(R.id.tab_my)
                        tab_my.isSelected = true
//                        (fragments[R.id.tab_my] as MyFragment).updateUI()
                    }
                }
            }
        }
    }

    private fun initFragment() {
        fragments[R.id.tab_home] = HomeFragment()
        fragments[R.id.tab_gold] = HomeFragment()
        fragments[R.id.tab_news] = NewsFragment()
        fragments[R.id.tab_my] = HomeFragment()

        mSelectedFragmentId = R.id.tab_my
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.rl_div, fragments[mSelectedFragmentId], mSelectedFragmentId.toString())
        ft.hide(fragments[mSelectedFragmentId])
        mSelectedFragmentId = R.id.tab_home
        ft.add(R.id.rl_div, fragments[mSelectedFragmentId], mSelectedFragmentId.toString())
        ft.commit()
        tab_home.isSelected = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tab_home -> {
//                val info = Information()
//                info.setAppkey("97862d0492a64bb7a6b5c9b95839c4b9")
//                SobotApi.startSobotChat(this, info)
//                LoginActivity.startAct(this)
                onChangeFragment(v)
                tab_home.isSelected = true
            }
            R.id.tab_gold -> {
//                CommodityTypeActivity.startAct(this)
                CommodityDetailActivity.startAct(this,"01")
//                AddressEditActivity.startAct(this)
//                GestureActivity.startAct(this)
//                onChangeFragment(v)
                tab_gold.isSelected = true
            }
            R.id.tab_my -> {
                ShoppingCartActivity.startAct(this)
//                GestureCreateActivity.startActivity(this)
//                onChangeFragment(v)
                tab_my.isSelected = true
            }
            R.id.tab_news -> {
                onChangeFragment(v)
                tab_news.isSelected = true
            }
        }
    }

    private fun onChangeFragment(v: View) {
        onChangeFragment(v.id)
    }


    private fun onChangeFragment(id: Int) {
        if (mSelectedFragmentId != id) {
            tab_home.isSelected = false
            tab_gold.isSelected = false
            tab_news.isSelected = false
            tab_my.isSelected = false
            val ft = supportFragmentManager.beginTransaction()
            val oldFragment = supportFragmentManager.findFragmentByTag(mSelectedFragmentId.toString())
            if (oldFragment != null) {
                ft.hide(oldFragment)
            }

            mSelectedFragmentId = id
            val newFragment = supportFragmentManager.findFragmentByTag(mSelectedFragmentId.toString())
            if (newFragment == null) {
                ft.add(R.id.rl_div, fragments[id], id.toString())
            } else {
                ft.show(newFragment)
            }
            ft.commit()
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

    var oldTime = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - oldTime) > 2000) {
                ToastUtil.getInstance().makeLongToast(this, "再按一次退出程序")
                oldTime = System.currentTimeMillis()
            } else {
                exitApp()
            }
        }
        return true
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }
}

