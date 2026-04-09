package com.example.wellnessapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val healthButton = findViewById<Button>(R.id.health_recipes)
        val nutritionButton = findViewById<Button>(R.id.nutrition_advice)
        val meditationButton = findViewById<Button>(R.id.meditation)
        val hydrationButton = findViewById<Button>(R.id.hydration_alert)
        val exerciseButton = findViewById<Button>(R.id.start_exercise)
        val motivationButton = findViewById<Button>(R.id.daily_motivation)
        val weeklyGoalsButton = findViewById<Button>(R.id.weekly_goals)
        val progressButton = findViewById<Button>(R.id.check_progress)
        val learnMoreButton = findViewById<Button>(R.id.learnmore)

        //  Set Up Click Listeners
        // 1. Health Navigation
        healthButton.setOnClickListener {
            val intent = Intent(this, HealthyActivity::class.java)
            startActivity(intent)
            showInterstitialAd()
        }

        // 2. Nutrition Navigation
        nutritionButton.setOnClickListener {
            val intent = Intent(this, NutritionActivity::class.java)
            startActivity(intent)
        }

        // 3. Meditation Navigation
        meditationButton.setOnClickListener {
            val intent = Intent(this, MeditationActivity::class.java)
            startActivity(intent)
        }

        // 4. Hydration Navigation
        hydrationButton.setOnClickListener {
            val intent = Intent(this, HydrationActivity::class.java)
            startActivity(intent)
        }

        // 5. Exercise Navigation
        exerciseButton.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        // 6. Motivation Navigation
        motivationButton.setOnClickListener {
            val intent = Intent(this, MotivationActivity::class.java)
            startActivity(intent)
        }

        // 7. Weekly Goals Navigation
        weeklyGoalsButton.setOnClickListener {
            val intent = Intent(this, WeeklyGoalsActivity::class.java)
            startActivity(intent)
        }

        // 8. Progress Navigation
        progressButton.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }

        // Learn More (External Website)
        learnMoreButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(intent)
        }

        // Banner Ad implementation
        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        loadInterstitialAd()
    }

    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }

    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}
