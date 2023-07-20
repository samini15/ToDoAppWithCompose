package com.example.todoappwithcompose.injection

import android.content.Context
import androidx.room.Room
import com.example.todoappwithcompose.data.ToDoDatabase
import com.example.todoappwithcompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        Constants.TODO_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()
}