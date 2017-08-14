package testapplication.imageviewer.application.di.components

import dagger.Component
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.di.scopes.PerParentScreen

/**
 * Created by root on 03.08.17.
 */
@PerParentScreen
@Component(modules = arrayOf(ParentScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface ParentScreenComponent {


}