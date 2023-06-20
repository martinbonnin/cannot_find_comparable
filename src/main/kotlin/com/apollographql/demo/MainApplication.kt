package com.apollographql.demo

import android.app.Application
import com.apollographql.apollo3.ApolloClient

class MainApplication: Application() {
    companion object {
        lateinit var apolloClient: ApolloClient
    }

    override fun onCreate() {
        super.onCreate()

        apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:4000/")
            .build()
    }
}