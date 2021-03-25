package com.siviwe.safoodprice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.extensions.loadImage
import com.siviwe.safoodprice.providers.Product
import com.siviwe.safoodprice.utils.getProgressDrawable

class ProductAdapter(val products: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun updateProducts(newProducts: ArrayList<Product>){
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product,parent,false)

        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.count()

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(products[position])

    inner class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val productName = view.findViewById<TextView>(R.id.productName)
        private val productPrice = view.findViewById<TextView>(R.id.productPrice)
        private val productStore = view.findViewById<TextView>(R.id.productStore)
        private val productImage = view.findViewById<ImageView>(R.id.productImage)

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = product.price
            productStore.text = product.store
            if (product.imageURL != null) {
                productImage.loadImage(product.imageURL, getProgressDrawable(productImage.context))
            } else {
                productImage.setImageDrawable(null)
            }
        }
    }
}
