package com.sk.panda.gold.ui.cart

import com.sk.panda.gold.base.contract.presenter.PresenterDataWrapper
import com.sk.panda.gold.entity.Goods
import com.sk.panda.gold.entity.Shop
import com.sk.panda.gold.entity.ShoppingGoods

/**
 * 购物车
 * @author joker
 * Email:812405389@qq.com
 * @version
 */
class ShoppingCartPresenter(v: ShoppingCartContract.View) :
        PresenterDataWrapper<ShoppingGoods, ShoppingCartContract.View>(v),
        ShoppingCartContract.Presenter<ShoppingGoods> {

    init {
        mData = ShoppingGoods()
        mData.shop = ArrayList()
    }

    override fun fetch() {
        var shop = Shop()
        shop.name = "永坤黄金"
        var sku = Goods()
        sku.name = "金条20g投资保值 女士金戒子"
        sku.spec = "20g"
        sku.size = "14cm"
        sku.price = 4022.01
        sku.inventory = 10
        sku.buyAmount = 2
        shop.goods.add(sku)
        sku = Goods()
        sku.name = "金条20g投资保值"
        sku.spec = "40g"
        sku.size = "15cm"
        sku.price = 8022.01
        sku.inventory = 1
        sku.buyAmount = 1
        shop.goods.add(sku)
        mData.shop!!.add(shop) // add one
        shop = Shop()
        shop.name = "XX黄金"
        sku = Goods()
        sku.name = "20g投资保值 女士金戒子"
        sku.spec = "10g"
        sku.size = "11cm"
        sku.price = 1022.01
        sku.inventory = 2
        sku.buyAmount = 1
        shop.goods.add(sku)
        mData.shop.add(shop) // add two

        mView.get()?.updateUI()

    }

    override fun refreshData() {
    }

    override fun getData(): ShoppingGoods {
        return mData
    }

    override fun errorData(error: Throwable) {
        super.errorData(error)
        mView.get()?.onError(error)
    }

    override fun close() {
        mView.clear()
    }
}