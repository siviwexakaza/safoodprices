package com.siviwe.safoodprice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.extensions.loadImage
import com.siviwe.safoodprice.providers.Product
import com.siviwe.safoodprice.utils.getProgressDrawable
import java.util.zip.Inflater

class ProductAdapter(val products: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view)

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

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.productName).text = products[position].name
        holder.view.findViewById<TextView>(R.id.productPrice).text = products[position].price
        holder.view.findViewById<TextView>(R.id.productStore).text = products[position].store
        holder.view.findViewById<ImageView>(R.id.productImage)
                .loadImage(products[position].imageURL!!, getProgressDrawable(holder.view.findViewById<ImageView>(R.id.productImage).context))
    }
}