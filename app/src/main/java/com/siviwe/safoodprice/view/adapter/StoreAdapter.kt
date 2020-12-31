package com.siviwe.safoodprice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.model.Store
import com.siviwe.safoodprice.view.StoresFragmentDirections

class StoreAdapter(val stores : ArrayList<Store>) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

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
        holder.view.findViewById<TextView>(R.id.storeName).text = stores[position].name
        holder.view.setOnClickListener {
            val action = StoresFragmentDirections.actionStoresFragmentToCategoriesFragment()
            action.store = stores[position].name.toString()
            Navigation.findNavController(it).navigate(action)
        }

    }
}