package com.aengussong.instacopy.utils

class StringCombiner(private val delimiter: String) {

    private lateinit var builder: StringBuilder

    fun combine(iterable: Iterable<String>): StringCombiner {
        iterable.forEach {
            append(it)
        }

        return this
    }

    fun append(s: String): StringCombiner {
        prepareBuilder().append(s)
        return this
    }

    private fun prepareBuilder(): StringBuilder {
        if (this::builder.isInitialized)
            builder.append(delimiter)
        else {
            builder = StringBuilder()
        }
        return builder
    }

    override fun toString(): String {
        return if (this::builder.isInitialized) builder.toString() else ""
    }
}