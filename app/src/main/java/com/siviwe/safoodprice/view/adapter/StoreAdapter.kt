package com.siviwe.safoodprice.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.providers.Store
import com.siviwe.safoodprice.view.StoresFragmentDirections

class StoreAdapter(val stores : ArrayList<Store>, private val onStoreSelected: (Store) -> Unit) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun updateStoresList(newStores : ArrayList<Store>){
        stores.clear()
        stores.addAll(newStores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun getItemCount() = stores.count()

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = stores[position]
        holder.view.findViewById<TextView>(R.id.storeName).text = store.name
        holder.view.setBackgroundColor(Color.parseColor(store.color))
        holder.view.setOnClickListener {
            onStoreSelected(store)
            val action = StoresFragmentDirections.actionStoresFragmentToCategoriesFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
