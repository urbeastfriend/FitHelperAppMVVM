package com.mvvm.fithelperapp.ui.recipesbycategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.api.FitHelperApi
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.databinding.ItemViewPagerRecipebycategoryBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

const val ARG_CATEGORY = "category"


class ViewPagerRecipesByCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.item_view_pager_recipebycategory,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_CATEGORY) }?.apply {
            val binding = ItemViewPagerRecipebycategoryBinding.bind(view)
            val category: Category = getParcelable<Category>(ARG_CATEGORY)!!
            val url: String = FitHelperApi.BASE_URL + category.thumbpath
            binding.apply {
                textCategory.text = category.description

                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_circle).resize(320,200)
                    .into(imageCategory, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(category.thumbpath).placeholder(R.drawable.ic_circle).resize(320,200).error(R.drawable.ic_error_recipe)
                                .into(imageCategory)
                        }
                    })

                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_circle).resize(320,200)
                    .into(imageCategoryBg, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                            Picasso.get().load(category.thumbpath).placeholder(R.drawable.ic_circle).resize(320,200).error(R.drawable.ic_error_recipe)
                                .into(imageCategoryBg)
                        }
                    })

            }
        }
    }
}