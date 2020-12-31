package com.siviwe.safoodprice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.view.adapter.StoreAdapter
import com.siviwe.safoodprice.viewmodel.StoresViewModel

class StoresFragment : Fragment() {

    private lateinit var viewModel: StoresViewModel
    private var storeAdapter = StoreAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stores, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StoresViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.storesRecyclerView)
        recView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storeAdapter

        }

        Observe()
    }

    fun Observe(){

        viewModel.isLoading.observe(this, Observer {
            if(it == false){
                view?.findViewById<ProgressBar>(R.id.storesProgressBar)?.visibility = View.GONE
            }
        })

        viewModel.stores.observe(this, Observer { stores ->
            stores?.let {
                storeAdapter.updateStoresList(stores)
            }
        })
    }



}