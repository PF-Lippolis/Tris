package com.example.tris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tris.databinding.ActivityShowResultBinding

class ShowResult : AppCompatActivity() {

    lateinit var binding: ActivityShowResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val winner = intent.extras!!.getInt("winner")
        when(winner) {
            1 -> binding.winner.setImageResource(R.drawable.x_mark)
            2 -> binding.winner.setImageResource(R.drawable.circle_mark)
            else -> {
                binding.victoryMessage.text = getString(R.string.draw)
                binding.other.visibility = View.VISIBLE
            }
        }

        binding.goBackButton.setOnClickListener { finish() }
    }
}