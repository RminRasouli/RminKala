package ir.project.rminkala.ui.Dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentDashboardBinding
import ir.project.rminkala.ui.Home.HomeFragment
import ir.project.rminkala.ui.Search.SearchFragment
import ir.project.rminkala.ui.Setting.SettingFragment

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDashboardBinding.bind(view)
        binding.cancelDashboard.setOnClickListener {

        }
        binding.settingDashboard.setOnClickListener {
            parentFragmentManager.commit {
                replace<SettingFragment>(R.id.frag_host)
                addToBackStack(null)
            }
        }
    }
}


















