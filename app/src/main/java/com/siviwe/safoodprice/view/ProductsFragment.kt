package com.siviwe.safoodprice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.siviwe.safoodprice.R
import com.siviwe.safoodprice.extensions.hide
import com.siviwe.safoodprice.extensions.visibleOrGone
import com.siviwe.safoodprice.view.adapter.ProductAdapter
import com.siviwe.safoodprice.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by navGraphViewModels(R.id.nav) {
        defaultViewModelProviderFactory
    }

    private var productsAdapter = ProductAdapter(arrayListOf())
    private var storeName = ""
    private var categoryName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            storeName = ProductsFragmentArgs.fromBundle(it).store
            categoryName = ProductsFragmentArgs.fromBundle(it).category

            view.findViewById<RecyclerView>(R.id.productsRecyclerView).adapter = productsAdapter

            observeData(storeName, categoryName, 0, productsAdapter.products.isEmpty())
        }
    }

    private fun observeData(shop: String, category: String, page: Int, showLoadingIndicator: Boolean){
        view?.findViewById<ProgressBar>(R.id.productsProgressBar)?.visibleOrGone(showLoadingIndicator)

        viewModel.getProducts(shop, category, page).observe(viewLifecycleOwner, Observer {
            view?.findViewById<ProgressBar>(R.id.productsProgressBar)?.hide()
            productsAdapter.updateProducts(ArrayList(it))
        })
    }
}
