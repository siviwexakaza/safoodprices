package com.siviwe.safoodprice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.view.adapter.StoreAdapter
import com.siviwe.safoodprice.viewmodel.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresFragment : Fragment() {
    private val viewModel: StoresViewModel by navGraphViewModels(R.id.nav) {
        defaultViewModelProviderFactory
    }
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

        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.storesRecyclerView)
        recView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storeAdapter

        }

        Observe()
    }

    fun Observe(){

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it == false){
                view?.findViewById<ProgressBar>(R.id.storesProgressBar)?.visibility = View.GONE
            }
        })

        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            stores?.let {
                storeAdapter.updateStoresList(stores)
            }
        })
    }



}
