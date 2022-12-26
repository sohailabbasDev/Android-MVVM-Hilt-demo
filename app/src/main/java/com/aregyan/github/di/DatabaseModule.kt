package com.aregyan.github.di

import android.content.Context
import androidx.room.Room
import com.aregyan.github.database.UsersDao
import com.aregyan.github.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
This is dependency injection using dagger hilt
*/
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    //This function provides App Database
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return Room.databaseBuilder(
            appContext,
            UsersDatabase::class.java,
            "Users"
        ).fallbackToDestructiveMigration().build()
    }


    //This function provides Data access object
    @Provides
    fun provideChannelDao(usersDatabase: UsersDatabase): UsersDao {
        return usersDatabase.usersDao
    }

}