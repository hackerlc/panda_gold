package com.sk.panda.gold.ui.login.lock

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.sk.panda.gold.R
import com.sk.panda.gold.base.App
import com.sk.panda.gold.base.activity.BasePActivity
import com.sk.panda.gold.config.AppConfig
import com.sk.panda.gold.entity.User
import com.sk.panda.gold.extended.onClickBind
import com.sk.panda.gold.ui.login.LoginActivity
import com.sk.panda.gold.utils.CommonUtils
import com.sk.panda.gold.utils.SPUtil
import com.sk.panda.gold.view.gestureview.LockPatternUtils
import com.sk.panda.gold.view.gestureview.LockPatternView
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_gesture.*

/**
 * 手势解锁
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class GestureActivity :
        BasePActivity<GestureContract.Presenter<User>>(R.layout.activity_gesture),
        GestureContract.View {
    private var account: String? = null
    private var mType: String? = null
    private lateinit var mShakeAnim: Animation

    private var mFailedPatternAttemptsSinceLastTimeout = 0

    companion object {
        /**
         * @param type 从哪个页面来的   main 首页  setting 设置
         */
        fun startAct(context: Context,type: String? = "other"){
                    context.startActivity(Intent(context, GestureActivity::class.java)
                            .putExtra("type",type))
        }
    }

    override fun attachPresenter() {
        mPresenter = GesturePresenter(this)
    }

    override fun initUI() {
        lockview.setOnPatternListener(mChooseNewLockPatternListener)
        lockview.isTactileFeedbackEnabled = true
        mType = intent.getStringExtra("type")
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_x)
        if (AppConfig.IS_LOGIN){
            account = AppConfig.I_USER?.phone
            if (account?.length == 11) {
                tv_user.text = CommonUtils.changeStrTrip(account)
            }
        }

        if ("setting" == mType){
            tv_other.visibility = View.GONE
        } else {
            tv_other.visibility = View.VISIBLE
        }

        onClickBind(this,tv_other)
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_other -> {
                App.mLockPatternUtils.clearLock(account)
                AppConfig.I_USER?.signLock = false
                SPUtil.saveObj(AppConfig.I_USER,SPUtil.USER_PSD)
                onError(Throwable())
            }
        }
    }

    override fun updateUI() {
        ToastUtil.getInstance().makeShortToast(this@GestureActivity, "解锁成功")
        finish()
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this, str)
    }

    override fun onError(error: Throwable) {
        AppConfig.IS_LOGIN = false
        SPUtil.setLogin(AppConfig.IS_LOGIN)
        LoginActivity.startAct(this)
        finish()
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

    private val mClearPatternRunnable = Runnable { lockview.clearPattern() }

    protected var mChooseNewLockPatternListener: LockPatternView.OnPatternListener = object : LockPatternView.OnPatternListener {

        override fun onPatternStart() {
            lockview.removeCallbacks(mClearPatternRunnable)
            patternInProgress()
        }

        override fun onPatternCleared() {
            lockview.removeCallbacks(mClearPatternRunnable)
        }

        override fun onPatternDetected(pattern: List<LockPatternView.Cell>?) {
            if (pattern == null)
                return

            if (App.mLockPatternUtils.checkPattern(pattern, account)) {
                if (mType == "setting") {
                    App.mLockPatternUtils.clearLock(account)
                    AppConfig.I_USER?.signLock = false
                    SPUtil.saveObj(AppConfig.I_USER,SPUtil.USER_PSD)
                    finish()
                } else if (mType == "main") {
                    //解锁成功后自动登录
                    mPresenter.fetch()
                } else if (mType == "other") {
                    ToastUtil.getInstance().makeShortToast(this@GestureActivity, "解锁成功")
                    finish()
                }
                lockview.setDisplayMode(LockPatternView.DisplayMode.Correct)
            } else {
                lockview.setDisplayMode(LockPatternView.DisplayMode.Wrong)
                if (pattern.size >= LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {
                    mFailedPatternAttemptsSinceLastTimeout++
                    val retry = LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT - mFailedPatternAttemptsSinceLastTimeout
                    if (retry > 0) {
                        tv_error.setText("手势绘制错误，还可以再输入" + retry + "次")
                        tv_error.startAnimation(mShakeAnim)
                    }
                } else {
                    ToastUtil.getInstance().makeShortToast(this@GestureActivity, "输入长度不够，请重试")
                }
                if (mFailedPatternAttemptsSinceLastTimeout >= LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT) {
                    App.mLockPatternUtils.clearLock(account)
                    AppConfig.I_USER?.signLock = false
                    SPUtil.saveObj(AppConfig.I_USER,SPUtil.USER_PSD)
                    showToast("手势密码错误次数过多，请重新登录!")
                    onError(Throwable())
                } else {
                    lockview.postDelayed(mClearPatternRunnable, 1000)
                }
            }
        }

        override fun onPatternCellAdded(pattern: List<LockPatternView.Cell>) {

        }

        private fun patternInProgress() {}
    }


    var oldTime = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mType == "main") {
                if ((System.currentTimeMillis() - oldTime) > 2000) {
                    ToastUtil.getInstance().makeLongToast(this, "再按一次退出程序")
                    oldTime = System.currentTimeMillis()
                } else {
                    exitApp()
                }
            } else {
                return super.onKeyDown(keyCode, event)
            }
        }
        return true
    }

}