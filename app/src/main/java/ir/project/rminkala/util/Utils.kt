package ir.project.rminkala.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.project.rminkala.data.model.error.ErrorApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.net.ssl.SSLException


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error<out T>(val message: String?) : ResultWrapper<T>()
    object Loading : ResultWrapper<Nothing>()
}

suspend inline fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline apiCall: suspend () -> Response<T>
) = flow {
    emit(ResultWrapper.Loading)
    try {
        val response=apiCall() //() after name or .invoke()
        val responseBody=response.body()
        if (response.isSuccessful && responseBody!=null) {
            emit(ResultWrapper.Success(responseBody))
        }else{
            val errorBody=response.errorBody()
            if (errorBody!=null){
                val type=object : TypeToken<ErrorApi>(){}.type
                val responseError= Gson().fromJson<ErrorApi>(errorBody.charStream(),type)
                emit(ResultWrapper.Error(responseError.message))
            }else{
                emit(ResultWrapper.Error<T>("error is here for you to smile"))
            }
        }
    } catch (e: SSLException) {
        emit(ResultWrapper.Error(e.message))
    }catch (e: IOException){
        emit(ResultWrapper.Error(e.message))
    }catch (e: HttpException){
        emit(ResultWrapper.Error(e.message))
    } catch (e:Throwable){
        emit(ResultWrapper.Error(e.message))
    }finally {
        // emit(ResultWrapper.Error("finally you .."))
    }
}

fun logger(msg: String) {
    Log.d("app_logger", msg)
}