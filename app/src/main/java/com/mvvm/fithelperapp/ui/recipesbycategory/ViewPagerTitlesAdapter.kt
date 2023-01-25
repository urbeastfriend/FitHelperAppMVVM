package com.mvvm.fithelperapp.ui.recipesbycategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.databinding.FragmentViewPagerTitleBinding

class ViewPagerTitlesAdapter(fragment: Fragment,_categories:List<Category>) : FragmentStateAdapter(fragment){

    private val categories = _categories

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerRecipesByCategoryFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(ARG_CATEGORY, categories[position])
        }
        return fragment
    }
}