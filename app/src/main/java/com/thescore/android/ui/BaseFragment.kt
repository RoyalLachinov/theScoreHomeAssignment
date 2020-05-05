package com.thescore.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.thescore.android.R
import com.thescore.android.constants.SharedPreferenceStorage
import com.thescore.android.viewmodel.TeamsViewModel

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

open class BaseFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var teamsViewModel: TeamsViewModel
    lateinit var sharedPreferencesStorage: SharedPreferenceStorage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.app_name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(view)
        teamsViewModel = (activity as MainActivity).teamsViewModel
        sharedPreferencesStorage = (activity as MainActivity).sharedPreferencesStorage

    }

}
