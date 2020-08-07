package fp

object Lambda {

    private fun sumByCondition(numbers: List<Int>, plusStrategy: (number: Int) -> Boolean): Int {
        return numbers.filter { plusStrategy(it) }.sum()
    }

    fun sumAll(numbers: List<Int>): Int = sumByCondition(numbers) { true }
    fun sumAllEven(numbers: List<Int>): Int = sumByCondition(numbers) { it % 2 == 0 }
    fun sumAllOverThree(numbers: List<Int>): Int = sumByCondition(numbers) { it > 3 }
}
