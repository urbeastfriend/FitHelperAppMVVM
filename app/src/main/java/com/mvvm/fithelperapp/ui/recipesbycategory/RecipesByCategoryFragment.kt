package com.mvvm.fithelperapp.ui.recipesbycategory

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.databinding.FragmentRecipesbycategoryBinding
import com.mvvm.fithelperapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList

@AndroidEntryPoint
class RecipesByCategoryFragment : Fragment(R.layout.fragment_recipesbycategory) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel: RecipesByCategoryViewModel by viewModels()
        val binding = FragmentRecipesbycategoryBinding.bind(view)
        val fragment = this


        binding.apply {

            viewModel.categories.observe(viewLifecycleOwner){
                if(it.isNotEmpty()) {
                    val viewPagerAdapter = ViewPagerTitlesAdapter(
                        fragment,
                        it
                    )
                    viewPager.adapter = viewPagerAdapter
                    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                        tab.text = it[position].name
                    }.attach()
                }
            }

        }


    }


}


