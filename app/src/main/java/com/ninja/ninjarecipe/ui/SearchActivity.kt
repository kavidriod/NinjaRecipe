package com.ninja.ninjarecipe.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ninja.ninjarecipe.adapter.SearchAdapter
import com.ninja.ninjarecipe.databinding.ActivitySearchBinding
import com.ninja.ninjarecipe.roomdb.AppDatabase
import com.ninja.ninjarecipe.roomdb.User
import com.ninja.ninjarecipe.roomdb.UserDao
import java.util.Locale

class SearchActivity : AppCompatActivity(){

    private lateinit var binding : ActivitySearchBinding
    private lateinit var searchRecipesEditText : EditText
    private lateinit var backImageview : ImageView
    private lateinit var searchRcView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchRecipesEditText = binding.searchRecipesEditText
        backImageview = binding.backImageview
        searchRcView = binding.searchRcView


        searchRcView.layoutManager = LinearLayoutManager(this)
        //Get data from database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db_name"
        ).allowMainThreadQueries()
            .createFromAsset("database/indianrecipe.db")
            .build()

        var userDao: UserDao = db.userDao()

        //get all recipes from database
        val recipes : List<User> = userDao.getAll()
                val searchAdapter  = SearchAdapter(recipes, applicationContext)
        searchRcView.adapter = searchAdapter

        backImageview.setOnClickListener {
            finish()
        }

        searchRecipesEditText.requestFocus()

        // Search from all recipes when EditText data changes
        searchRecipesEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Method required*
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Method required*
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) { // Search if new alphabet added
                    filter(s.toString())
                }
            }

            private fun filter(toString: String) {
                var filteredList = mutableListOf<User>()
                for (recipe in recipes.indices) {
if(recipes.get(recipe).tittle.lowercase(Locale.ROOT).contains(toString.lowercase())){
    filteredList.add(recipes.get(recipe))
}
                }
                searchAdapter.updateFilteredList(filteredList)
            }
        })
    }
}