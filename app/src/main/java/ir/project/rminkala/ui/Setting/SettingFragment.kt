package ir.project.rminkala.ui.Setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.data.local.data_store.Theme
import ir.project.rminkala.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {


    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingBinding.bind(view)

        init()
    }

    private fun init() = with(binding) {
        settingThemes.setOnCheckedChangeListener { radioGroup, checkedId ->
            val theme = when (checkedId) {
                light.id -> Theme.LIGHT
                night.id -> Theme.NIGHT
                else -> Theme.AUTO
            }
            viewModel.updateTheme(theme)
        }
    }
}