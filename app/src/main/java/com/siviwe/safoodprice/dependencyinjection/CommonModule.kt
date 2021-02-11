package com.siviwe.safoodprice.dependencyinjection

import com.siviwe.safoodprice.providers.*
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

    @Binds
    fun bindsProductsProvider(impl: ProductsProviderImpl): ProductsProvider
}
