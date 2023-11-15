package ru.nsu.ftt


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nsu.fit.timetable.presentation.TimeTableFragment
import ru.nsu.ftt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentMain, TimeTableFragment()).commit()
        }
    }
}