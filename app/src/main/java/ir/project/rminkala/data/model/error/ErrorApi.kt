package ir.project.rminkala.data.model.error

data class ErrorApi(
    val code: String,
    val `data`: Data,
    val message: String
)