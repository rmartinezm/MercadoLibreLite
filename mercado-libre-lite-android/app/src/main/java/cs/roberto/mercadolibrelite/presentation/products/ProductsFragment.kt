package cs.roberto.mercadolibrelite.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.databinding.FragmentProductsBinding
import cs.roberto.mercadolibrelite.presentation.products.adapter.ItemReferenceAdapter
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/** */
class ProductsFragment : Fragment() {

    /* */
    private val binding: FragmentProductsBinding
            by lazy { FragmentProductsBinding.inflate(layoutInflater) }

    /* */
    private val args: ProductsFragmentArgs by navArgs()

    /* */
    private val productsViewModel: ProductsViewModel by viewModel { parametersOf(args.query) }

    /* */
    private lateinit var itemReferenceAdapter: ItemReferenceAdapter

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        execute()
    }

    /** */
    private fun setupViews() {
        setupToolbar()
        setupProductsRecyclerView()
        setupPullToRefresh()
    }

    /** */
    private fun setupToolbar() {
        binding.mtToolbar.apply {
            title = args.query
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    /** */
    private fun setupProductsRecyclerView() {
        itemReferenceAdapter = ItemReferenceAdapter(::onItemReferenceActionClickListener)
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemReferenceAdapter
        }
    }

    /** */
    private fun setupPullToRefresh() {
        binding.srlRefresh
            .setOnRefreshListener(productsViewModel::invalidateDataSource)
    }

    /** */
    private fun execute() {
        productsViewModel.getItemReferencesPagedListAsLiveData(
            ::onGetItemsReferencesFailure,
            ::onGetItemsReferencesEmptyResults
        ).observe(viewLifecycleOwner) { onGetItemsReferencesDone(it) }
    }

    /** */
    private fun onGetItemsReferencesFailure(failure: GetItemsReferencesFailure) {
        requireActivity().runOnUiThread {
            when (failure) {
                is GetItemsReferencesFailure.DetailFailure ->
                    manageDetailsFailure(failure)
                GetItemsReferencesFailure.NetworkConnectionFailure ->
                    showNetworkConnectionFailureLayout()
            }
        }
    }

    /** */
    private fun manageDetailsFailure(failure: GetItemsReferencesFailure.DetailFailure) {
        val message: String = getString(R.string.details_failure, failure.detail)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /** */
    private fun onGetItemsReferencesEmptyResults() {
        requireActivity().runOnUiThread {
            showEmptyListMessageLayout()
        }
    }

    /** */
    private fun onGetItemsReferencesDone(itemsReferences: PagedList<ItemReference>) {
        itemReferenceAdapter.submitList(itemsReferences)
        goneNetworkConnectionFailureLayout()
        gonePullToRefreshLoader()
    }

    /** */
    private fun onItemReferenceActionClickListener(itemReference: ItemReference) {
        val direction = ProductsFragmentDirections
            .actionProductsFragmentToProductDetailsActivity(itemReference.id)
        findNavController().navigate(direction)
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
    private fun showEmptyListMessageLayout() {
        binding.layoutEmptyListMessage.root.visibility = View.VISIBLE
    }

    /** */
    private fun gonePullToRefreshLoader() {
        binding.srlRefresh.isRefreshing = false
    }

    /** */
    private fun onBackPressed() {
        findNavController().navigateUp()
    }

}