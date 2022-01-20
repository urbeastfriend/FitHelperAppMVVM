package com.mvvm.fithelperapp.ui.recipesbycategory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.databinding.FragmentRecipesbycategoryBinding
import com.mvvm.fithelperapp.ui.home.HomeViewModel

class RecipesByCategoryFragment : Fragment(), OnCategoryDescriptionClickListener{


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel: RecipesByCategoryViewModel by viewModels()

        val binding = FragmentRecipesbycategoryBinding.bind(view)
        val categoriesAdapter = ViewPagerCategoriesAdapter(this)


        binding.apply {

        }

        viewModel.categories.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                categoriesAdapter.submitList(it)
            }

        }
    }

    override fun onShowFullCategoryDescriptionClick(category: Category) {
        TODO("Not yet implemented")
    }
}