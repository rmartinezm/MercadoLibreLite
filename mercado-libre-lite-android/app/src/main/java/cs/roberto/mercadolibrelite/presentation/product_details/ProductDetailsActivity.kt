package cs.roberto.mercadolibrelite.presentation.product_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.core.clean.presentation.Status
import cs.roberto.mercadolibrelite.databinding.ActivityProductDetailsBinding
import cs.roberto.mercadolibrelite.presentation.product_details.adapter.SliderImagesAdapter
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemDetails
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details.GetItemDetailsStatus
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
        setupViews()
        executeIfItsRequired()
    }

    /** */
    private fun setupViews() {
        setupToolbar()
        setupPullToRefresh()
        setupActions()
    }

    /** */
    private fun setupToolbar() {
        binding.layoutProductDetailsAppbar
            .mtToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    /** */
    private fun setupPullToRefresh() {
        binding.srlRefresh.setOnRefreshListener(::execute)
    }

    /** */
    private fun setupActions() {
        binding.layoutProductDetailsInformation
            .mbSeeInMercadolibreAction.setOnClickListener(::onSeeInMercadolibreActionClickListener)
    }

    /** */
    private fun executeIfItsRequired() {
        val itemDetails = productDetailsViewModel.itemDetails
        if (itemDetails == null)
            execute()
        else manageGetItemDetailsDone(itemDetails)
    }

    /** */
    private fun execute() {
        productDetailsViewModel.getItemDetailsAsLiveData()
            .observe(this, createGetItemDetailsStatusObserver())
    }

    /** */
    private fun createGetItemDetailsStatusObserver() = Observer<GetItemDetailsStatus> {
        when (it) {
            is Status.Loading -> setLoadingModeAsTrue()
            is Status.Failed -> manageGetItemDetailsFailure(it.failure)
            is Status.Done -> manageGetItemDetailsDone(it.value.itemDetails)
        }
    }

    /** */
    private fun manageGetItemDetailsFailure(failure: GetItemDetailsFailure) {
        setLoadingModeAsFalse()
        when (failure) {
            is GetItemDetailsFailure.DetailFailure ->
                manageDetailsFailure(failure)
            GetItemDetailsFailure.NetworkConnectionFailure ->
                showNetworkConnectionFailureLayout()
        }
    }

    /** */
    private fun manageDetailsFailure(failure: GetItemDetailsFailure.DetailFailure) {
        val message: String = getString(R.string.details_failure, failure.detail)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /** */
    private fun manageGetItemDetailsDone(itemDetails: ItemDetails) {
        binding.itemDetails = itemDetails
        val sliderAdapter = SliderImagesAdapter(itemDetails.picturesUrls)
        binding.svImages.setSliderAdapter(sliderAdapter)
        setLoadingModeAsFalse()
        goneNetworkConnectionFailureLayout()
        binding.llProductDetailsContainer.visibility = View.VISIBLE
    }

    /** */
    private fun setLoadingModeAsTrue() {
        binding.apply {
            layoutItemDetailsLoader.root.visibility = View.VISIBLE
            llProductDetailsContainer.visibility = View.GONE
        }
    }

    /** */
    private fun setLoadingModeAsFalse() {
        binding.apply {
            srlRefresh.isRefreshing = false
            layoutItemDetailsLoader.root.visibility = View.GONE
        }
    }

    /** */
    private fun showNetworkConnectionFailureLayout() {
        binding.layoutNetworkConnectionFailure.root.visibility = View.VISIBLE
    }

    /** */
    private fun goneNetworkConnectionFailureLayout() {
        binding.layoutNetworkConnectionFailure.root.visibility = View.GONE
    }

    /** */
    private fun onSeeInMercadolibreActionClickListener(view: View) {
        val url: String? = binding.itemDetails?.permalink
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(it) }
            startActivity(intent)
        }
    }

}