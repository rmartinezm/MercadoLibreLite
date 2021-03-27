package cs.roberto.mercadolibrelite.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** */
class SplashFragment : Fragment() {

    /* */
    private val binding: FragmentSplashBinding
            by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    /* */
    private val splashTime: Long = 2_000L

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSearchAfterSplashTime()
    }

    /** */
    private fun navigateToSearchAfterSplashTime() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(splashTime)
            findNavController().navigate(R.id.action_splashFragment_to_searchFragment)
        }
    }

}