package com.ninja.ninjarecipe.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room.databaseBuilder
import com.ninja.ninjarecipe.adapter.CatergoryAdapter
import com.ninja.ninjarecipe.databinding.ActivityCategoryBinding
import com.ninja.ninjarecipe.roomdb.AppDatabase
import com.ninja.ninjarecipe.roomdb.User
import com.ninja.ninjarecipe.roomdb.UserDao

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var backBtnImageView : ImageView
    private lateinit var titleTextView : TextView
    private lateinit var categoryRecView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtnImageView = binding.backBtnImageView
        titleTextView = binding.titleTextView

        categoryRecView = binding.categoryRecView
        categoryRecView.layoutManager = LinearLayoutManager(this)

        val category = intent.getStringExtra("Category")
        val title = intent.getStringExtra("Title")

        titleTextView.text = title


        // Get database
        val db: AppDatabase = databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db_name"
        ).allowMainThreadQueries()
            .createFromAsset("database/indianrecipe.db")
            .build()
        val userDao: UserDao = db.userDao()

        val recipes = userDao.getAll()
        val recipesBasedOnCategory = mutableListOf<User>()

        for (recipe in recipes) {
            if(recipe.category.contains(category.toString())) {
                recipesBasedOnCategory.add(recipe)
            }
        }


        Log.i("Good", "recipesBasedOnCategory: "+recipesBasedOnCategory.size)
        val categoryAdapter = CatergoryAdapter(recipesBasedOnCategory, applicationContext)
        categoryRecView.adapter = categoryAdapter

        backBtnImageView.setOnClickListener { v -> finish() }

    }

}
