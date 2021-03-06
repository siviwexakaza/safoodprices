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
    private var storeAdapter = StoreAdapter(arrayListOf()) { store ->
        viewModel.selectedStore = store
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recView = view.findViewById<RecyclerView>(R.id.storesRecyclerView)
        recView.adapter = storeAdapter

        observeData(storeAdapter.stores.isEmpty())
    }

    private fun observeData(showLoadingIndicator: Boolean){
        if (showLoadingIndicator) {
            view?.findViewById<ProgressBar>(R.id.storesProgressBar)?.visibility = View.VISIBLE
        }

        viewModel.getStores().observe(viewLifecycleOwner, Observer { stores ->
            view?.findViewById<ProgressBar>(R.id.storesProgressBar)?.visibility = View.GONE
            storeAdapter.updateStoresList(ArrayList(stores))
        })
    }
}
