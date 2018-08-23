package com.sk.panda.gold.ui.commodity.detail

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.Commodity
import com.sk.panda.gold.ui.commodity.detail.CommodityDetailContract

/**
 * 标的详情
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class CommodityDetailPresenter(v: CommodityDetailContract.View, var id :String) :
        PresenterDataWrapper<Commodity, CommodityDetailContract.View>(v),
        CommodityDetailContract.Presenter<Commodity> {

    init {
        mData = Commodity()
    }
//    val mNM = CommodityNM()

    override fun fetch() {
        mView.get()?.onDialog(true)
//        mNM.getFinancialDetail(id)
//                .compose(mView.get()!!.getLifecycleDestroy())
//                .compose(RxSchedulersHelper.io_main())
//                .compose(SchedulersDataHelper.handleResult())
//                .subscribe({ processData(it) },
//                        { errorData(it) })
    }

    override fun processData(d: Commodity) {
        super.processData(d)
        mView.get()?.onDialog(false)
        mData = d
        mView.get()?.updateUI()
    }

    override fun refreshData() {
    }

    override fun getData(): Commodity {
        return mData
    }

    override fun errorData(error: Throwable) {
        super.errorData(error)
        mView.get()?.onDialog(false)
        mView.get()?.onError(error)
    }

    override fun close() {
        mView.clear()
    }
}