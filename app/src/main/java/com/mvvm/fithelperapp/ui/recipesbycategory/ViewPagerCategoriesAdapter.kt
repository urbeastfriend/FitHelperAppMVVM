package com.mvvm.fithelperapp.ui.recipesbycategory


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.api.FitHelperApi
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.databinding.ItemViewPagerRecipesbycategoryBinding
import com.mvvm.fithelperapp.ui.home.CategoriesRecyclerViewAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class ViewPagerCategoriesAdapter(
    private val listener: OnCategoryDescriptionClickListener) :
    ListAdapter<Category, ViewPagerCategoriesAdapter.CategoriesViewHolder>(
        CategoriesRecyclerViewAdapter.DiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemViewPagerRecipesbycategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CategoriesViewHolder(private val binding: ItemViewPagerRecipesbycategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                textCategory.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val categoryClicked = getItem(position)
                        listener.onShowFullCategoryDescriptionClick(categoryClicked)
                    }
                }
            }
        }

        fun bind(category: Category) {
            binding.apply {
                val url: String = FitHelperApi.BASE_URL + category.thumbpath

                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.ic_circle).resize(320, 200)
                    .into(imageCategory, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(url).placeholder(R.drawable.ic_circle)
                                .resize(320, 200)
                                .into(imageCategory)
                        }
                    })
                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.ic_circle).resize(320, 200)
                    .into(imageCategoryBg, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(url).placeholder(R.drawable.ic_circle)
                                .resize(320, 200)
                                .into(imageCategoryBg)
                        }
                    })

                textCategory.text = category.description


            }
        }
    }
}

interface OnCategoryDescriptionClickListener{

    fun onShowFullCategoryDescriptionClick(category: Category)
}

