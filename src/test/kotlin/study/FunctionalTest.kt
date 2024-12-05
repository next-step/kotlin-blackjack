package study

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FunctionalTest {
    @Test
    fun sumAllTest() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val result1 = sumAll(numbers)
        val result2 = sumAllLambda(numbers)
        assertTrue(result1 == 15)
        assertTrue(result2 == 15)
    }

    @Test
    fun sumAllEvenLambdaTest() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val result1 = sumAllEven(numbers)
        val result2 = sumAllEvenLambda(numbers)
        assertTrue(result1 == 12)
        assertTrue(result2 == 12)
    }

    @Test
    fun sumAllOverThreeTest() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val result1 = sumAllOverThree(numbers)
        val result2 = sumAllOverThreeLambda(numbers)
        assertTrue(result1 == 15)
        assertTrue(result2 == 15)
    }
}
