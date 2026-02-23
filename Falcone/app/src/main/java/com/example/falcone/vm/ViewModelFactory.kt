package com.example.falcone.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.falcone.di.ActivityScope
import com.example.falcone.di.ApplicationScope
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@ApplicationScope
class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator  = creators[modelClass]

        if(creator == null){
            creators.forEach{
                if(modelClass.isAssignableFrom(it.key)){
                    creator = it.value
                    return@forEach
                }
            }
        }

        return if (creator == null) {
            try {
                modelClass.newInstance()
            } catch (e: InstantiationException) {
                throw ViewModelCreationException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw ViewModelCreationException("Cannot create an instance of $modelClass", e)
            }
        } else try {
            creator!!.get() as T
        } catch (e: Exception) {
            throw ViewModelCreationException("Cannot create an instance of $modelClass", e)
        }
    }
}

class ViewModelCreationException(message: String?, cause: Throwable?) : RuntimeException(message, cause)

