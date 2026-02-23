package com.example.falcone.di

import javax.inject.Scope


@kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ApplicationScope

@kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ActivityScope