package com.mvvm.fithelperapp.ui.mainactivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.mvvm.fithelperapp.R
import com.mvvm.fithelperapp.databinding.ActivityMainBinding
import com.mvvm.fithelperapp.util.ApiCallState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setSupportActionBar(binding.toolbar)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment
        ),binding.drawerLayout
        )


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.detailsFragment){
                binding.toolbar.visibility = View.GONE
            }
            else{
                binding.toolbar.visibility = View.VISIBLE
            }
        }


        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        subscribeObservers()
        setEventChannelListener()
        //viewModel.onDbUpdateRequired()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState){
                is ApiCallState.Success ->{
                    viewModel.onApiCallSuccess()
                }
                is ApiCallState.Error ->{
                    viewModel.onApiCallError(dataState.exception)
                }
                is ApiCallState.Loading ->{
                    viewModel.onApiCallLoading()
                }
            }
        })
    }

    private fun setEventChannelListener(){
        lifecycleScope.launchWhenStarted {

            viewModel.mainEvent.collect{event: MainEvent ->
                when (event) {
                    is MainEvent.ShowUpdateResultMessage ->
                    {
                        val parentLayout: View = binding.root
                        Snackbar.make(parentLayout,event.msg,Snackbar.LENGTH_LONG).show()
                    }
                    is MainEvent.ShowProgressBar ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainEvent.HideProgressBar ->{
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}