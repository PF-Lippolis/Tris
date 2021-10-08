package com.example.tris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val tris = Tris()
    val cells = listOf(
        listOf(binding.c00, binding.c01, binding.c02),
        listOf(binding.c10, binding.c11, binding.c12),
        listOf(binding.c20, binding.c21, binding.c22))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindCells()
    }

    private fun showResult(result: Int) {

    }

    private fun bindCells() {
        for((i, row) in cells.withIndex()) {
            for((j, c) in row.withIndex())
            c.apply {
                setOnClickListener {
                    if(tris.player == 1) {
                        setImageResource(R.drawable.x_mark)
                    } else {
                        setImageResource(R.drawable.circle_mark)
                    }
                    val result = tris.play(i, j)
                    showResult(result)
                }
            }
        }
    }
}