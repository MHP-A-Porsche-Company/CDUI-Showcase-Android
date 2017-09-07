package com.mhp.showcase.module

import com.mhp.showcase.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Assigns references in activities, services, or fragments to have access to singletons earlier defined.
 */
@Singleton
@Component(modules = arrayOf(ShowcaseModule::class))
interface ShowcaseComponent {
     fun inject(homeFragment: HomeFragment)
}