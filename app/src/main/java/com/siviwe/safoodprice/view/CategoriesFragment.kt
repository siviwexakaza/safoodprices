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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val viewModel: CategoriesViewModel by navGraphViewModels(R.id.nav) {
        defaultViewModelProviderFactory
    }
    private var storeName = ""
    private var categoriesAdapter = CategoryAdapter(ArrayList(),storeName)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            storeName = CategoriesFragmentArgs.fromBundle(it).store
            categoriesAdapter.setStoreValue(storeName)

            val categoriesRecView = view.findViewById<RecyclerView>(R.id.categoriesRecyclerView).apply {
                adapter = categoriesAdapter
            }

            observeData(storeName)
        }
    }

    fun observeData(storeName: String){

        viewModel.getCategories(storeName).observe(viewLifecycleOwner, Observer {
            view?.findViewById<ProgressBar>(R.id.categoriesProgressBar)?.visibility = View.GONE
            categoriesAdapter.updateCategories(ArrayList(it))
        })
    }
}
