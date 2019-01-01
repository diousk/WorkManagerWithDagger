package com.example.david_chen.workmanagerplusdagger.di

import com.example.david_chen.workmanagerplusdagger.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by david.chen@soocii.me on 2018/11/8.
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}