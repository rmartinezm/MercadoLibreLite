package cs.roberto.mercadolibrelite.presentation.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

/** */
class SplashFragment : Fragment() {

    /* */
    private val binding: FragmentSplashBinding
            by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    /* */
    private val splashTime: Long = 2_000L

    /* */
    private var currentJob: Job? = null

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentJob = navigateToSearchAfterSplashTime()
    }

    /** */
    private fun navigateToSearchAfterSplashTime(): Job =
        CoroutineScope(Dispatchers.IO).launch {
            delay(splashTime)
            findNavController().navigate(R.id.action_splashFragment_to_searchFragment)
        }

    /** */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentJob?.cancel()
    }

}