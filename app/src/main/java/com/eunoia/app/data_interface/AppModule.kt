package com.eunoia.app.data_interface

import com.eunoia.app.authentication.data.AuthRepositoryImpl
import com.eunoia.app.authentication.domain.AuthRepository
import com.eunoia.app.user.data.repository.UserRepositoryImpl
import com.eunoia.app.user.domain.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}