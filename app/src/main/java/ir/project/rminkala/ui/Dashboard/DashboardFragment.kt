package ir.project.rminkala.ui.Dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentDashboardNotverifyBinding
import ir.project.rminkala.ui.Confirm.ConfirmFragment
import ir.project.rminkala.ui.Detail.DetailFragmentDirections
import ir.project.rminkala.ui.Login.LoginViewModel
import ir.project.rminkala.ui.Setting.SettingFragment
import kotlinx.coroutines.launch

const val TAG = "Dashboard Fragment"

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard_notverify) {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindingDashboardNotverifyBinding = FragmentDashboardNotverifyBinding.bind(view)
        bindingDashboardNotverifyBinding.cancelDashboard.setOnClickListener {
            lifecycleScope.launch {
                bindingDashboardNotverifyBinding.customerId.text = "Loading ..."
                loginViewModel.enterInfoCustomer("AcRIAM","RAScSSOLI","RAjgvncuevj@gmail.com","cBOBOBOBO")
                bindingDashboardNotverifyBinding.customerId.text = loginViewModel.customerId.toString()
            }
        }
        bindingDashboardNotverifyBinding.settingDashboard.setOnClickListener {
            parentFragmentManager.commit {
                replace<SettingFragment>(R.id.frag_host)
                addToBackStack(null)
            }
        }

        bindingDashboardNotverifyBinding.apply {
            ButtonConfirm.setOnClickListener {
                if(emailInput.text.toString() != ""){
                    val action = DashboardFragmentDirections.actionDashboardFragmentToConfirmFragment(
                        emailInput.text.toString()
                    )
                    it.findNavController().navigate(
                        action
                    )
                }else{
                    Toast.makeText(requireContext(), "لطفا ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}


















