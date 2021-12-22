package com.mvvm.fithelperapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), CategoriesRecyclerViewAdapter.OnHomeRVClickListener,RecipesViewPagerAdapter.OnHomeVPListener{

    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)

        val categoriesAdapter = CategoriesRecyclerViewAdapter(this)
        val recipesAdapter = RecipesViewPagerAdapter(this)


        binding.apply {
            recyclerCategory.apply {
                adapter = categoriesAdapter
                layoutManager = GridLayoutManager(requireContext(),3,
                GridLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                isNestedScrollingEnabled = true

            }
            viewPagerHeader.apply {
                adapter = recipesAdapter
                setPadding(20,0,150,0)
            }

        }

        viewModel.categories.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                binding.shimmerCategory.root.visibility = View.INVISIBLE
                categoriesAdapter.submitList(it)
            }

        }
        viewModel.recipes.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                binding.shimmerMeal.root.visibility = View.INVISIBLE
                recipesAdapter.submitList(it)
            }
        }

    }

    override fun onCategoryClick(category: Category) {
        viewModel.onCategorySelected(category)
    }

    override fun onRecipeClick(recipe: Recipe) {
        viewModel.onRecipeSelected(recipe)
    }
}