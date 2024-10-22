package com.ninja.ninjarecipe.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ninja.ninjarecipe.databinding.CategotyListBinding
import com.ninja.ninjarecipe.roomdb.User
import com.ninja.ninjarecipe.ui.RecipeActivity

class CatergoryAdapter(private val receipes: List<User>,
                       private  val applicationContext: Context?)
    : RecyclerView.Adapter<CatergoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: CategotyListBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("Good", "onCreateViewHolder: "+receipes.size)
        val binding = CategotyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.i("Good", "getItemCount: "+receipes.size)
        return receipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eachRecipe = receipes[position]

        Log.i("Good", "onBindViewHolder: ")
        holder.binding.txt1.text = eachRecipe.tittle

        val ingList = eachRecipe.ing.split("\n");

        holder.binding.catgorytimeTextView.text = ingList[0]

        Glide.with(applicationContext!!).load(eachRecipe.img).into(holder.binding.img)


        holder.binding.cardBtn.setOnClickListener { v ->
            val intent: Intent = Intent(applicationContext, RecipeActivity::class.java)
            intent.putExtra("img", eachRecipe.img)
            intent.putExtra("tittle", eachRecipe.tittle)
            intent.putExtra("des",eachRecipe.des)
            intent.putExtra("ing", eachRecipe.ing) // Ingredients
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }

    }


}