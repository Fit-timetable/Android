package ru.nsu.ftt


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.presentation.register.RegisterFragment
import ru.nsu.fit.common.FttRouter
import ru.nsu.fit.common.FttScreens
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
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentMain, RegisterFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun CoroutineScope.monitoringRouter() {
        launch {
            router.currentScreen.collect {
                when (it) {
                    FttScreens.AuthScreen -> {

                    }

                    FttScreens.RegisterScreen -> {

                    }

                    FttScreens.ScheduleScreen -> {

                    }
                }
            }
        }
    }

    private fun replaceCurrnetFragment(tag: String, fragmentFactory: () -> Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentMain, fragmentFactory())
            .commit()
    }
}