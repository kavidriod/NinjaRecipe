package com.ninja.ninjarecipe.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ninja.ninjarecipe.R
import com.ninja.ninjarecipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    private lateinit var recipeImg: ImageView
    private lateinit var backBtnImg: ImageView
    private lateinit var overlayImg: ImageView

    private lateinit var recipetitle : TextView
    private lateinit var time: TextView
    private lateinit var ing: TextView
    private lateinit var steps: TextView

    private lateinit var btnStep : Button
    private lateinit var btnIng : Button

    private val isImgCrop = false

    lateinit var ingList : Array<String>

    private lateinit var scrollView: ScrollView
    private lateinit var scrollView_step: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeImg = binding.recipeImg
        backBtnImg = binding.backBtn
        overlayImg = binding.imageGradient

        recipetitle = binding.tittle
        time = binding.time
        ing = binding.ing
        steps = binding.stepsTxt

        btnStep = binding.stepsBtn
        btnIng = binding.ingBtn

        btnStep =  binding.stepsBtn
        btnIng = binding.ingBtn

        scrollView = binding.ingScroll
        scrollView_step = binding.steps

        //load recipe image from link
        Glide.with(applicationContext).load(intent.getStringExtra("img"))
        .into(recipeImg);

        recipetitle.text = intent.getStringExtra("tittle")


        // Set recipe ingredients
        ingList =
            intent.getStringExtra("ing")!!.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

        // Set time
        time.text = ingList[0]

        for (i in 1 until ingList.size) {
            ing.text = ing.text.toString() + "\uD83D\uDFE2  " + ingList[i] + "\n"
        }

        steps.text = intent.getStringExtra("des")

        btnStep.background = null

        backBtnImg.setOnClickListener {
            finish()
        }

        btnStep.setOnClickListener(View.OnClickListener { v: View? ->
            btnStep.setBackgroundResource(R.drawable.btn_ing)
            btnStep.setTextColor(getColor(R.color.white))
            btnIng.background = null
            btnIng.setTextColor(getColor(R.color.black))

            scrollView.visibility = View.GONE
            scrollView_step.visibility = View.VISIBLE
        })

        btnIng.setOnClickListener(View.OnClickListener { v: View? ->
            btnIng.setBackgroundResource(R.drawable.btn_ing)
            btnIng.setTextColor(getColor(R.color.white))
            btnStep.background = null
            btnStep.setTextColor(getColor(R.color.black))

            scrollView.visibility = View.VISIBLE
            scrollView_step.visibility = View.GONE
        })

        }
    }