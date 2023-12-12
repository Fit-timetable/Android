package ru.nsu.ftt


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.presentation.auth.AuthFragment
import ru.nsu.fit.auth.presentation.register.RegisterFragment
import ru.nsu.fit.common.FttRouter
import ru.nsu.fit.common.FttScreens
import ru.nsu.fit.timetable.presentation.TimeTableFragment
import ru.nsu.ftt.databinding.ActivityMainBinding
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    internal lateinit var router: FttRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    monitoringRouter()
                }
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentMain, AuthFragment())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun CoroutineScope.monitoringRouter() {
        launch {
            router.currentScreen.collect {
                when (it) {
                    FttScreens.AuthScreen -> {
                        replaceCurrentFragment("AuthScreen") {
                            AuthFragment()
                        }
                    }

                    FttScreens.RegisterScreen -> {
                        replaceCurrentFragment("RegisterScreen", true) {
                            RegisterFragment()
                        }
                    }

                    FttScreens.ScheduleScreen -> {
                        replaceCurrentFragment("ScheduleScreen") {
                            TimeTableFragment()
                        }
                    }
                }
            }
        }
    }

    private fun replaceCurrentFragment(
        tag: String,
        addToBackStack: Boolean = false,
        fragmentFactory: () -> Fragment,
    ) {
        val tr = supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .apply {
                if (addToBackStack) {
                    Log.d("FTT_TAG", "add fragment $tag")
                    add(R.id.fragmentMain, fragmentFactory(), tag)
                    addToBackStack(tag)
                } else {
                    Log.d("FTT_TAG", "replase fragment to $tag")
                    replace(R.id.fragmentMain, fragmentFactory(), tag)
                }
            }

        tr.commit()
    }
}