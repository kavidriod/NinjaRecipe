package com.ninja.ninjarecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ninja.ninjarecipe.databinding.SearchListBinding
import com.ninja.ninjarecipe.roomdb.User
import com.ninja.ninjarecipe.ui.RecipeActivity

class SearchAdapter(private var userList: List<User>,
    private val applicationContext: Context?) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(val binding: SearchListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int  = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eachRecipe = userList[position]

        Glide.with(holder.binding.searchImg.context).load(eachRecipe.img).into(holder.binding.searchImg)
        holder.binding.searchTxt.setText(eachRecipe.tittle)
        holder.binding.searchItem.setOnClickListener { v: View? ->
            val intent = Intent(applicationContext, RecipeActivity::class.java)
            intent.putExtra("img", eachRecipe.img)
            intent.putExtra("tittle", eachRecipe.tittle)
            intent.putExtra("des", eachRecipe.des)
            intent.putExtra("ing", eachRecipe.ing)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext!!.startActivity(intent)
        }
    }

    fun updateFilteredList(filteredList: MutableList<User>) {
        userList = filteredList
        notifyDataSetChanged()
    }
}