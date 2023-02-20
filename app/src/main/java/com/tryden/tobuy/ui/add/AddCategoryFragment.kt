package com.tryden.tobuy.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tryden.tobuy.database.entity.CategoryEntity
import com.tryden.tobuy.databinding.FragmentAddCategoryBinding
import com.tryden.tobuy.ui.BaseFragment
import java.util.UUID

class AddCategoryFragment : BaseFragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryNameEditText.requestFocus()
        mainActivity.showKeyboard()
        binding.saveButton.setOnClickListener {
            saveToCategoryDatabase()
        }

        sharedViewModel.transactionCompleteLiveData.observe(viewLifecycleOwner) { event ->
            event.getContent()?.let {
                navigateUp()
            }
        }
    }

    private fun saveToCategoryDatabase() {
        val categoryName = binding.categoryNameEditText.text.toString().trim()
        if (categoryName.isEmpty()) {
            binding.categoryNameTextField.error = "* Required field"
            return
        }

        val categoryEntity = CategoryEntity(
            id = UUID.randomUUID().toString(),
            name = categoryName
        )

        sharedViewModel.insertCategory(categoryEntity)
    }


    override fun onResume() {
        super.onResume()
        mainActivity.hideKeyboard(requireView())
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}