package com.example.tris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.tris.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var active = AtomicBoolean(true)

    val tris = Tris()
    lateinit var cells: List<List<ImageView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        cells = listOf(
            listOf(binding.c00, binding.c01, binding.c02),
            listOf(binding.c10, binding.c11, binding.c12),
            listOf(binding.c20, binding.c21, binding.c22))
        setContentView(binding.root)

        bindCells()
    }

    private fun showResult(result: Int) {
        for(row in cells) {
            for(cell in row) {
                cell.setImageResource(0)
            }
        }
        tris.resetGrid()
        bindCells()
        startActivity(Intent(this, ShowResult::class.java).apply {
            putExtra("winner", result)
        })
    }

    private fun bindCells() {
        for((i, row) in cells.withIndex()) {
            for((j, c) in row.withIndex())
            c.apply {
                setOnClickListener {
                    if(active.compareAndSet(true, false)) {
                        lifecycleScope.launch(Dispatchers.Default) {
                            delay(500L)
                            active.set(true)
                        }
                        if(tris.player == 1) {
                            setImageResource(R.drawable.x_mark)
                        } else {
                            setImageResource(R.drawable.circle_mark)
                        }
                        val result = tris.play(i, j)
                        setOnClickListener(null)
                        if(result != 0) showResult(result)
                    }
                }
            }
        }
    }
}