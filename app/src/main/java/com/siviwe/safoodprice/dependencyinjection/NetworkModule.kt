package com.siviwe.safoodprice.dependencyinjection

import com.siviwe.safoodprice.network.APIService
import com.siviwe.safoodprice.network.APIServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface NetworkModule {

    @Binds
    fun bindsAPIService(impl: APIServiceImpl): APIService
}
