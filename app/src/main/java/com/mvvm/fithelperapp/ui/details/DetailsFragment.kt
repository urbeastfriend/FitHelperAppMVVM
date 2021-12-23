package com.mvvm.fithelperapp.ui.details

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.api.Client
import com.mvvm.fithelperapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()
    lateinit var binding: FragmentDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentDetailsBinding.bind(view)
        setupActionBar()
        val menu: Menu


        binding.apply {

            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            toolbar.inflateMenu(R.menu.detail_menu)
            menu = toolbar.menu

            toolbar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_setfavorite -> {

                        true
                    }
                    R.id.action_eatthis -> {

                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }

            val favoriteItem: MenuItem = menu.findItem(R.id.action_setfavorite)
            val eatThis: MenuItem = menu.findItem(R.id.action_eatthis)

            val favoriteItemColor: Drawable = favoriteItem.icon

            setupColorActionBarIcon(favoriteItemColor)

            if (viewModel.favorite) {
                favoriteItem.setIcon(R.drawable.ic_favorite)
            } else {
                favoriteItem.setIcon(R.drawable.ic_favorite_border)
            }

            collapsingToolbar.title = viewModel.name
            category.text = viewModel.category
            country.text = viewModel.country
            instructions.text = viewModel.instuctions
            calories.text = viewModel.calories
            protein.text = viewModel.protein
            fats.text = viewModel.fats
            carbs.text = viewModel.carbs
            cooktime.text = viewModel.cooktime
            ingredient.text = viewModel.ingredients
            measure.text = viewModel.measures

            if (viewModel.thumbpath.isNotBlank()) {
                Picasso.get().load(Client.BASE_URL + viewModel.thumbpath)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(mealThumb, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {
                            Picasso.get().load(Client.BASE_URL + viewModel.thumbpath)
                                .into(mealThumb)
                        }
                    })
            }
            progressBar.visibility = View.INVISIBLE
        }



    }


    private fun setupActionBar() {

        binding.apply {
            collapsingToolbar.setContentScrimColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorWhite
                )
            )
            collapsingToolbar.setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            collapsingToolbar.setExpandedTitleColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorWhite
                )
            )
        }


    }

    private fun setupColorActionBarIcon(favoriteItemColor: Drawable) {
        binding.apply {
            appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if ((collapsingToolbar.height + verticalOffset) < (2 * ViewCompat.getMinimumHeight(
                        collapsingToolbar
                    ))
                ) {

                    if (toolbar.navigationIcon != null) {
                        toolbar.navigationIcon?.colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                        favoriteItemColor.mutate().colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                    }

                } else {
                    if (toolbar.navigationIcon != null) {
                        toolbar.navigationIcon?.colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                        favoriteItemColor.mutate().colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                    }
                }
            })
        }
    }


}