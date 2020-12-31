package com.siviwe.safoodprice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.siviwe.safoodprice.R


class ProductsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        val backButton = view.findViewById<Button>(R.id.btnBack)
        backButton.setOnClickListener {
            val action = ProductsFragmentDirections.actionProductsFragmentToCategoriesFragment()
            Navigation.findNavController(it).navigate(action)
        }
        return view
    }


}