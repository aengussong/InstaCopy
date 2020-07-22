package com.aengussong.instacopy.utils

import org.junit.Assert
import org.junit.Test

private const val DEFAULT_DELIMITER = "|"

internal class StringCombinerTest {

    @Test
    fun `get string from empty combiner - should return empty string`() {
        val combiner = StringCombiner(DEFAULT_DELIMITER)
        Assert.assertEquals("", combiner.toString())
    }

    @Test
    fun `get string from single append combiner - should return string without delimiter`() {
        val combiner = StringCombiner(DEFAULT_DELIMITER)
        val input = "word"

        combiner.append(input)

        Assert.assertEquals(input, combiner.toString())
    }

    @Test
    fun `append several words - delimiter should be between appended words`() {
        val word1 = "word"
        val word2 = "word2"
        val word3 = "word3"
        val combiner = StringCombiner(DEFAULT_DELIMITER)

        combiner.append(word1).append(word2).append(word3)

        val delimitersCount = combiner.getDelimitersCount()
        Assert.assertEquals(2, delimitersCount)
    }

    @Test
    fun `combine iterable - delimiter should be between each item`() {
        val words = listOf("word1", "word2", "word4", "word4")
        val combiner = StringCombiner(DEFAULT_DELIMITER)

        combiner.combine(words)

        val delimitersCount = combiner.getDelimitersCount()
        Assert.assertEquals(3, delimitersCount)
    }

    @Test
    fun `append several words - string representation should not end with delimiter`() {
        val words = listOf("word1", "word2", "word3")
        val combiner = StringCombiner(DEFAULT_DELIMITER)

        combiner.combine(words)

        Assert.assertFalse(combiner.toString().endsWith(DEFAULT_DELIMITER))
    }

    private fun StringCombiner.getDelimitersCount() =
        toString().toCharArray().filter { c: Char -> c.toString() == DEFAULT_DELIMITER }.count()
}