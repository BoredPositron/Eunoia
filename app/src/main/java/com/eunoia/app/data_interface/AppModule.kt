package com.eunoia.app.data_interface

import com.eunoia.app.authentication.data.AuthRepositoryImpl
import com.eunoia.app.authentication.domain.AuthRepository
import com.eunoia.app.user.registration.data.repository.UserRepositoryImpl
import com.eunoia.app.user.registration.domain.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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

    @Provides
    fun provideCloudStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun provideDb(): FirebaseFirestore = FirebaseFirestore.getInstance()


}