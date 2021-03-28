package cs.roberto.mercadolibrelite.presentation.product_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.navArgs
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.core.clean.presentation.Status
import cs.roberto.mercadolibrelite.databinding.ActivityProductDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/** */
class ProductDetailsActivity : AppCompatActivity() {

    /* */
    private val binding: ActivityProductDetailsBinding
            by lazy { ActivityProductDetailsBinding.inflate(layoutInflater) }

    /* */
    private val args: ProductDetailsActivityArgs by navArgs()

    /* */
    private val productDetailsViewModel: ProductDetailsViewModel
            by viewModel { parametersOf(args.itemId) }

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        productDetailsViewModel.getItemDetailsAsLiveData().observe(this) {
            Log.e("PRODUCT_DETAIL", it.toString())
            if (it is Status.Done)
                binding.tvLabel.text = it.value.itemDetails.toString()
        }
    }

}