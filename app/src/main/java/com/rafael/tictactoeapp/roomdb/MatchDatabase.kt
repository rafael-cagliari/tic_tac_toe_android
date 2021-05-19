package com.rafael.tictactoeapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafael.tictactoeapp.model.Match

@Database(entities = [Match::class], version=1, exportSchema = false)
abstract class MatchDatabase: RoomDatabase() {


    abstract fun matchDao(): MatchDao

    companion object{
        @Volatile
        private var INSTANCE: MatchDatabase? = null

        fun getDatabase(context: Context): MatchDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchDatabase::class.java,
                    "match_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}