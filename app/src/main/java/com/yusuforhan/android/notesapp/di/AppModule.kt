package com.yusuforhan.android.notesapp.di

import android.content.Context
import androidx.room.Room
import com.yusuforhan.android.notesapp.common.Constants.NOTES_TABLE_NAME
import com.yusuforhan.android.notesapp.data.data_source.NoteDatabase
import com.yusuforhan.android.notesapp.data.repository.NoteRepositoryImpl
import com.yusuforhan.android.notesapp.domain.repository.NoteRepository
import com.yusuforhan.android.notesapp.domain.use_case.AddNoteUseCase
import com.yusuforhan.android.notesapp.domain.use_case.DeleteNoteUseCase
import com.yusuforhan.android.notesapp.domain.use_case.GetNoteUseCase
import com.yusuforhan.android.notesapp.domain.use_case.GetNotesUseCase
import com.yusuforhan.android.notesapp.domain.use_case.NoteUseCases
import com.yusuforhan.android.notesapp.presentation.navigaiton.addNoteRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NOTES_TABLE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(database.noteDao())

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
}