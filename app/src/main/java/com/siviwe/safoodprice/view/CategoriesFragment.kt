package com.siviwe.safoodprice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.siviwe.safoodprice.R

class CategoriesFragment : Fragment() {

    private var storeName = ""

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

        arguments?.let {

            storeName = CategoriesFragmentArgs.fromBundle(it).store
            view.findViewById<TextView>(R.id.txtStoreName).text = storeName
        }

        val homeButton = view.findViewById<Button>(R.id.btnHome)
        homeButton.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToStoresFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val productsButton = view.findViewById< Button>(R.id.btnProducts)
        productsButton.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}