package com.example.falcone.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.falcone.model.PlanetDetails
import com.example.falcone.model.VehicleDetails

@Database(entities = [PlanetDetails::class, VehicleDetails::class], version = 1)
abstract class FalconDatabase : RoomDatabase() {

    abstract fun falconDao(): FalconDao

    companion object{

        @Volatile
        var INSTANCE: FalconDatabase? = null

        fun getDatabase(context: Context): FalconDatabase{
            return INSTANCE ?: synchronized(this){

                INSTANCE  = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FalconDatabase::class.java,
                    "falcon_database")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }

}
