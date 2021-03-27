package cs.roberto.mercadolibrelite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.databinding.ActivityMainBinding

/** */
class MainActivity : AppCompatActivity() {

    /* */
    private val binding: ActivityMainBinding
            by lazy { ActivityMainBinding.inflate(layoutInflater) }

    /* */
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    /* */
    private val navController: NavController
            by lazy { navHostFragment.navController }

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}