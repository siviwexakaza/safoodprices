package com.siviwe.safoodprice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.providers.Category
import com.siviwe.safoodprice.providers.Store
import com.siviwe.safoodprice.view.CategoriesFragmentDirections

class CategoryAdapter(val categories: ArrayList<Category>, var store: Store): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateCategories(newCategories: ArrayList<Category>){
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categories.count()

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.categoryName).text = categories[position].name
        holder.view.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment()
            action.store = store.route ?: ""
            action.category = categories[position].name.toString()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
