package com.mvvm.fithelperapp.ui.recipesbycategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.api.FitHelperApi
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.databinding.ItemRecyclerviewRecipebycategoryBinding

import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class RecyclerViewRecipesByCategoryAdapter(private val listener: OnRecipeRVClickListener) :
    ListAdapter<Recipe, RecyclerViewRecipesByCategoryAdapter.RecipesViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecyclerviewRecipebycategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class RecipesViewHolder(private val binding: ItemRecyclerviewRecipebycategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val recipeClicked = getItem(position)
                        listener.onRecipeClick(recipeClicked)
                    }
                }
                love.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val recipeClicked = getItem(position)
                        listener.onMarkRecipeAsFavoriteClick(recipeClicked)
                    }
                }
            }
        }

        fun bind(recipe: Recipe){
            binding.apply {
                val url: String = FitHelperApi.BASE_URL + recipe.thumbpath
                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_circle).resize(320,200)
                    .into(mealThumb, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(url).placeholder(R.drawable.ic_circle).resize(320,200)
                                .into(mealThumb)
                        }
                    })

                mealName.text = recipe.name
                matching.text = ""

                if(recipe.favorite){
                    love.setImageResource(R.drawable.ic_favorite)
                }
                else{
                    love.setImageResource(R.drawable.ic_favorite_border)
                }
            }
        }

    }
}

interface OnRecipeRVClickListener{

    fun onRecipeClick(recipe: Recipe)
    fun onMarkRecipeAsFavoriteClick(recipe: Recipe)
}

class DiffCallBack : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}