package com.siviwe.safoodprice.dependencyinjection

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface CommonModule {
    //TODO Bind common reusable components here
}
