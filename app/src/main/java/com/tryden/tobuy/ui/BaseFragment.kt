package com.tryden.tobuy.ui

import androidx.fragment.app.Fragment
import com.tryden.tobuy.database.AppDatabase

class BaseFragment : Fragment() {

    protected val mainActivity: MainActivity
        get() = activity as MainActivity

    protected val appDatabase: AppDatabase
        get() = AppDatabase.getDatabase(requireActivity())
}