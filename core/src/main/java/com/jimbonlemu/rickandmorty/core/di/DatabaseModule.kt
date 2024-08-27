package com.jimbonlemu.rickandmorty.core.di

import android.content.Context

import androidx.room.Room
import com.jimbonlemu.rickandmorty.core.data.local.room.CharacterDao
import com.jimbonlemu.rickandmorty.core.data.local.room.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "character.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes("jimbonlemu".toCharArray())))
            .build()
    @Provides
    fun provideCharacterDao(db: CharacterDatabase): CharacterDao = db.characterDao()
}