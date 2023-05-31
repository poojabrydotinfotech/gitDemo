package com.example.testgitdemo

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDataBase:RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object {

        private var instance: NoteDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): NoteDataBase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, NoteDataBase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: NoteDataBase) {
            val noteDao = db.noteDao()
            subscribeOnBackground {
                noteDao.insert(NoteModel("title 1",))
                noteDao.insert(NoteModel("title 2"))
                noteDao.insert(NoteModel("title 3"))

            }
        }
    }



    /*  fun getDatabase(
          context: Context,
          scope: CoroutineScope
      ): NoteDataBase {
          // if the INSTANCE is not null, then return it,
          // if it is, then create the database
          return INSTANCE ?: synchronized(this) {
              val instance = Room.databaseBuilder(
                  context.applicationContext,
                  NoteDataBase::class.java,
                  "word_database"
              )
                  // Wipes and rebuilds instead of migrating if no Migration object.
                  // Migration is not part of this codelab.
                  .fallbackToDestructiveMigration()
                  .addCallback(WordDatabaseCallback(scope))
                  .build()
              INSTANCE = instance
              // return instance
              instance
          }
      }
  }

  private class WordDatabaseCallback(
      private val scope: CoroutineScope
  ) : RoomDatabase.Callback() {
      /**
       * Override the onCreate method to populate the database.
       */
      override fun onCreate(db: SupportSQLiteDatabase) {
          super.onCreate(db)
          // If you want to keep the data through app restarts,
          // comment out the following line.
          INSTANCE?.let { database ->
              scope.launch(Dispatchers.IO) {
                  populateDatabase(database.noteDao())
              }
          }
      }

      private fun populateDatabase(noteDao: NoteDao) {
         noteDao.insert(NoteModel("title1"))
          noteDao.insert(NoteModel("title2"))
          noteDao.insert(NoteModel("title1"))

      }


  }*/

}
