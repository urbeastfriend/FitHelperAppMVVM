package com.mvvm.fithelperapp.ui.details

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.mvvm.fithelperapp.api.FitHelperApi


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentDetailsBinding.bind(view)
        setupActionBar()
        val menu: Menu


        binding.apply {

            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                //activity?.onBackPressed()
                findNavController().popBackStack()
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

            val favoriteItem: MenuItem = toolbar.menu.findItem(R.id.action_setfavorite)
            val eatThisItem: MenuItem = toolbar.menu.findItem(R.id.action_eatthis)
            setupColorActionBarIcon(favoriteItem,eatThisItem)


            viewModel.state.observe(viewLifecycleOwner){
                it?.let {
                    collapsingToolbar.title = it.name
                    category.text = it.category
                    country.text = it.country
                    instructions.text = it.instructions
                    calories.text = it.calories
                    protein.text = it.protein
                    fats.text = it.fats
                    carbs.text = it.carbs
                    cooktime.text = it.cooktime
                    ingredient.text = it.ingredients
                    measure.text = it.measures
                    favoriteItem.setIcon( if(it.favorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
                    if(it.thumbpath.isNotBlank())
                    {
                        Picasso.get().load(FitHelperApi.BASE_URL + it.thumbpath)
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(mealThumb, object : Callback {
                                override fun onSuccess() {

                                }

                                override fun onError(e: Exception?) {
                                    Picasso.get().load(FitHelperApi.BASE_URL + it.thumbpath)
                                        .into(mealThumb)
                                }
                            })
                    }
                }
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

    private fun setupColorActionBarIcon(favoriteItem: MenuItem, eatThisItem: MenuItem) {
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

                        favoriteItem.icon.mutate()
                            .colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                        eatThisItem.icon.mutate()
                            .colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                            BlendMode.SRC_ATOP
                        )
                    }

                } else {
                    if (toolbar.navigationIcon != null) {
                        toolbar.navigationIcon?.colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorWhite),
                            BlendMode.SRC_ATOP
                        )

                        favoriteItem.icon.mutate()
                            .colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorWhite),
                            BlendMode.SRC_ATOP
                        )
                        eatThisItem.icon.mutate()
                            .colorFilter = BlendModeColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.colorWhite),
                            BlendMode.SRC_ATOP
                        )
                    }
                }
            })
        }
    }


}