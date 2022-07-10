package ir.project.rminkala.ui.Confirm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentComfirmBinding
import ir.project.rminkala.ui.Login.LoginViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmFragment : Fragment(R.layout.fragment_comfirm) {
    private val loginViewModel: LoginViewModel by viewModels()
    private val args: ConfirmFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentComfirmBinding.bind(view)


        binding.emailSabti.text = args.email
        binding.apply {
            val firstName = firstNameFiled.text.toString()
            val lastName = lastNameFiled.text.toString()
            val userName = UserNameFiled.text.toString()
            val email = args.email
            ButtonConfirm.setOnClickListener {
                    prograssBar.isVisible = true
                    lifecycleScope.launch {
                        loginViewModel.enterInfoCustomer(firstName, lastName, email, userName)
                    }
                    if (loginViewModel.customerId != 0) {
                        Toast.makeText(
                            requireContext(),
                            "ثبت نام شما با شماره ${loginViewModel.customerId} با موفقیت ب ثبت رسید",
                            Toast.LENGTH_SHORT
                        ).show()
                        prograssBar.isVisible = false
                    }
                }

            vasiat.setOnClickListener {
                vasiat.text = loginViewModel.loginState.toString()
            }
        }
    }

}