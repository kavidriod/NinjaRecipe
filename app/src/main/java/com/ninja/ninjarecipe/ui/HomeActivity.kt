package com.ninja.ninjarecipe.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.airbnb.lottie.LottieAnimationView
import com.ninja.ninjarecipe.R
import com.ninja.ninjarecipe.adapter.PopularAdapter
import com.ninja.ninjarecipe.databinding.ActivityHomeBinding
import com.ninja.ninjarecipe.roomdb.AppDatabase
import com.ninja.ninjarecipe.roomdb.User
import kotlinx.coroutines.NonCancellable.start

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var salad: ImageView
    private lateinit var main: ImageView
    private lateinit var drinks: ImageView
    private lateinit var menu: ImageView
    private lateinit var dessert: ImageView
    private lateinit var rcview_popular : RecyclerView
    private lateinit var editText: EditText
    private lateinit var lottie: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        salad = binding.salad
        main = binding.MainDish
        drinks = binding.Drinks
        menu = binding.imageView
        dessert = binding.Desserts

        editText = binding.editText
        lottie = binding.lottie

        rcview_popular = binding.rcviewPopular
        rcview_popular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //set Popular Recipes
        setPopularRecipes()

        salad.setOnClickListener { v -> openEachCategory("Salad", "Salad") }
        main.setOnClickListener { v -> openEachCategory("Dish", "Main Dish") }
        drinks.setOnClickListener { v -> openEachCategory("Drinks", "Drinks") }
        dessert.setOnClickListener { v -> openEachCategory("Dessert", "Dessert") }

        editText.setOnClickListener { v ->
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        menu.setOnClickListener { v ->
            showAboutApp()
        }

    }

    private fun showAboutApp() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.side_menu)

        val  aboutApp = dialog.findViewById<LinearLayout>(R.id.about_app)
        aboutApp.setOnClickListener { v ->
            val intent = Intent(this, AboutAppActivity::class.java)
            startActivity(intent)
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun openEachCategory(category: String, title: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("Category",category)
        intent.putExtra("Title",title)
        startActivity(intent)
    }

    private fun setPopularRecipes() {
        //Get data from database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db_name"
        ).allowMainThreadQueries()
            .createFromAsset("database/indianrecipe.db")
            .build()

        val userDao = db.userDao()

        //get all recipes from database
        val recipes : List<User> = userDao.getAll()

        //filter popular once
        var popularRecipes = mutableListOf<User>()
        for (recipe in recipes){
            if(recipe.category.contains("Popular")){
                popularRecipes.add(recipe)
            }
        }

        //set popular list to adapter
        val adapter = PopularAdapter(popularRecipes, applicationContext)
        rcview_popular.adapter = adapter
    }
}