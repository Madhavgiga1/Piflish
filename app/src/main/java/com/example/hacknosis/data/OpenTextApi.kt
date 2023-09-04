package com.example.hacknosis.data

import retrofit2.http.GET


/*You define an interface that describes the API endpoints you want to interact with
. This interface includes method definitions for various HTTP requests (e.g., GET, POST, PUT, DELETE) and their associated parameters.
* */
interface OpenTextApi {
    @GET("")
    fun fetchText(){
        return
    }

}