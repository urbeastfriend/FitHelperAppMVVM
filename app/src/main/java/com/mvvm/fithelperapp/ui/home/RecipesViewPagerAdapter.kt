package com.mvvm.fithelperapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.api.Client


import com.mvvm.fithelperapp.data.Recipes.Recipe

import com.mvvm.fithelperapp.databinding.ItemViewPagerHeaderBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class RecipesViewPagerAdapter(
    private val listener : OnHomeVPListener) : ListAdapter<Recipe, RecipesViewPagerAdapter.RecipesViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemViewPagerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    inner class RecipesViewHolder(private val binding: ItemViewPagerHeaderBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener{
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val recipeclicked = getItem(position)
                        listener.onRecipeClick(recipeclicked)
                    }
                }
            }
        }
        fun bind(recipe: Recipe){

            binding.apply {
                recipeName.text = recipe.name

                val url: String = Client.BASE_URL + recipe.thumbpath

                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_circle).resize(320,200)
                    .into(recipeThumb, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(url).placeholder(R.drawable.ic_circle).resize(320,200)
                                .into(recipeThumb)
                        }
                    })
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id  == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }



    interface OnHomeVPListener{

        fun onRecipeClick(recipe: Recipe)
    }
}