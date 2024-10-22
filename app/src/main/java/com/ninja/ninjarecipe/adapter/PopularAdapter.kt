package com.ninja.ninjarecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ninja.ninjarecipe.databinding.PopularListBinding
import com.ninja.ninjarecipe.roomdb.User
import com.ninja.ninjarecipe.ui.RecipeActivity

class PopularAdapter(private val popularRecipes: List<User>,
                     private  val applicationContext: Context?)
    : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    class ViewHolder(val binding: PopularListBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularRecipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val temp = popularRecipes[position]

        // Split the time from ingredients
        val time = temp.ing.split("\n")
        holder.binding.popularTime.text =   "\uD83D\uDD50 ${time[0]}"

        // Load image from link
        Glide.with(holder.binding.popularTime.context).load(temp.img).into(holder.binding.popularImg)

        // Set title
        holder.binding.popularTxt.text = temp.tittle


        holder.binding.popularImg.setOnClickListener  {
            val intent = Intent(applicationContext, RecipeActivity::class.java).apply {
                putExtra("img", temp.img)
                putExtra("tittle", temp.tittle)
                putExtra("des", temp.des)
                putExtra("ing", temp.ing)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            applicationContext?.startActivity(intent)
        }
    }


}