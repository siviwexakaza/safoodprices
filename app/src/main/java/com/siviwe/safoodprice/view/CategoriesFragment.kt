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
import com.siviwe.safoodprice.view.adapter.CategoryAdapter
import com.siviwe.safoodprice.viewmodel.CategoriesViewModel
import com.siviwe.safoodprice.viewmodel.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val viewModel: CategoriesViewModel by navGraphViewModels(R.id.nav) {
        defaultViewModelProviderFactory
    }
    private val storesViewModel: StoresViewModel by navGraphViewModels(R.id.nav) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storesViewModel.selectedStore?.let {
            val categoriesAdapter = CategoryAdapter(ArrayList(), it)
            view.findViewById<RecyclerView>(R.id.categoriesRecyclerView).adapter = categoriesAdapter

            observeData(categoriesAdapter, it.name)
        }
    }

    private fun observeData(adapter: CategoryAdapter, storeName: String){
        viewModel.getCategories(storeName).observe(viewLifecycleOwner, Observer {
            view?.findViewById<ProgressBar>(R.id.categoriesProgressBar)?.visibility = View.GONE
            adapter.updateCategories(ArrayList(it))
        })
    }
}
