package ir.project.rminkala.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

//@Database(entities = [ProductLocalModel::class], version = 1 )
abstract class ProductDataBase : RoomDatabase() {
//
//    abstract fun productDao(): ProductDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ProductDataBase? = null
//
//        fun getDatabase(context: Context): ProductDataBase {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ProductDataBase::class.java,
//                    "word_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
        }
//    }
//}













