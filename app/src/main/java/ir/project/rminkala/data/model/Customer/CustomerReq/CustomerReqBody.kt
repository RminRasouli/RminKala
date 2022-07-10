package ir.project.rminkala.data.model.Customer.CustomerReq

data class CustomerReqBody(
    val email: String,
    val first_name: String,
    val last_name: String,
    val username: String
)