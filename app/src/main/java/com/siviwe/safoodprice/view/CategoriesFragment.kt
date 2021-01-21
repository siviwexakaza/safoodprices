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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.view.adapter.CategoryAdapter
import com.siviwe.safoodprice.viewmodel.CategoriesViewModel

class CategoriesFragment : Fragment() {

    private var storeName = ""
    private lateinit var categoriesViewModel: CategoriesViewModel
    private var categoriesAdapter = CategoryAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)

        arguments?.let {

            storeName = CategoriesFragmentArgs.fromBundle(it).store
            categoriesViewModel.refresh(storeName)

            val categoriesRecView = view.findViewById<RecyclerView>(R.id.categoriesRecyclerView).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = categoriesAdapter
            }
            Observe()

        }


    }

    fun Observe(){

        categoriesViewModel.isLoading.observe(this, Observer {
            if(it == false){
                view?.findViewById<ProgressBar>(R.id.categoriesProgressBar)?.visibility = View.GONE
            }
        })

        categoriesViewModel.categories.observe(this, Observer {
            it?.let {
                categoriesAdapter.updateCategories(it)
            }
        })

    }

}