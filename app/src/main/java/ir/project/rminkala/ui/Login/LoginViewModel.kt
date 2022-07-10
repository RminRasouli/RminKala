package ir.project.rminkala.ui.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.Customer.CustomerReq.CustomerReqBody
import ir.project.rminkala.data.repository.Repository
import ir.project.rminkala.ui.Dashboard.TAG
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private var _loginState: Boolean = false
    val loginState get() = _loginState

    private var _customerId: Int = 0
    val customerId get() = _customerId

    private var _firstName: String = ""
    val firstName get() = _firstName
    private var _lastName: String = ""
    val lastName get() = _lastName
    var _email: String = ""
    val email get() = _email
    private var _userName: String = ""
    val userName get() = _userName

    private var _address: String = ""
    val address get() = _address
    private var _city: String = ""
    val city get() = _city
    private var _country: String = ""
    val country get() = _country
    private var _postCode: String = ""
    val postCode get() = _postCode
    private var _phone: String = ""
    val phone get() = _phone


    var error: String = ""

    suspend fun enterInfoCustomer(
        first_name: String,
        last_name: String,
        email: String,
        user_name: String
    ) {
        _firstName = first_name
        _lastName = last_name
        _email = email
        _userName = user_name
        createCustomer()
    }

    suspend fun createCustomer() {

        val customerReqBody = CustomerReqBody(
            email,
            firstName,
            lastName,
            userName
        )
        val response = repository.createCustomer(customerReqBody)

        if (response.isSuccessful) {
            _loginState = true
            _customerId = (response.body()?.id ?: Int) as Int

        }
        fun setEmail(email: String) {
            _email = email
            Log.d(TAG, "setEmail: $email ")
            Log.d(TAG, "setEmail: $_email ")
        }


    }
}


