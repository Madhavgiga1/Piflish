package com.example.hacknosis.util
/*
A sealed class in Kotlin is a special type of class that allows you to define a restricted hierarchy of subclasses.
It means that all the subclasses of a sealed class must be defined within the same file where the sealed class is declared.
 Sealed classes are useful for representing a fixed set of related types or states, making your code more concise and ensuring
 that you handle all possible cases when working with these types.
* */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Loading<T>: NetworkResult<T>()

}

/*NetworkResult is a sealed class that represents the result of a network call. It has three subclasses: Success, Error, and Loading.
By making NetworkResult a sealed class and using subclasses to represent the different states of
a network call, we can ensure that all possible outcomes of the network call are accounted for and handled appropriately in our code.
* */