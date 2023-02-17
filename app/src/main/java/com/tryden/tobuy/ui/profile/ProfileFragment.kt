package com.tryden.tobuy.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tryden.tobuy.R
import com.tryden.tobuy.arch.ToBuyViewModel
import com.tryden.tobuy.databinding.FragmentProfileBinding
import com.tryden.tobuy.ui.BaseFragment

class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileEpoxyController = ProfileEpoxyController(
        onCategoryEmptyStateClicked = ::onCategoryStateClicked
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.epoxyRecyclerView.setController(profileEpoxyController)

        sharedViewModel.categoryEntitiesLiveData.observe(viewLifecycleOwner) { categoryEntityList ->
            profileEpoxyController.categories = categoryEntityList
        }
    }

    private fun onCategoryStateClicked() {
        navigateViaNavGraph(R.id.action_profileFragment_to_addCategoryFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}