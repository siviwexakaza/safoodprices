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
    private var categoriesAdapter = CategoryAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            storeName = CategoriesFragmentArgs.fromBundle(it).store
            viewModel.refresh(storeName)

            val categoriesRecView = view.findViewById<RecyclerView>(R.id.categoriesRecyclerView).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = categoriesAdapter
            }
            Observe()

        }


    }

    fun Observe(){

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it == false){
                view?.findViewById<ProgressBar>(R.id.categoriesProgressBar)?.visibility = View.GONE
            }
        })

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                categoriesAdapter.updateCategories(it)
            }
        })

    }

}
