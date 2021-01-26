package com.siviwe.safoodprice.dependencyinjection

import com.siviwe.safoodprice.providers.CategoriesProvider
import com.siviwe.safoodprice.providers.CategoriesProviderImpl
import com.siviwe.safoodprice.providers.StoresProvider
import com.siviwe.safoodprice.providers.StoresProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface CommonModule {

    @Binds
    fun bindsStoresProvider(impl: StoresProviderImpl): StoresProvider

    @Binds
    fun bindsCategoriesProvider(impl: CategoriesProviderImpl): CategoriesProvider
}
