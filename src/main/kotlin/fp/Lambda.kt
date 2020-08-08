package fp

class Lambda {
    fun sumAll(numbers: List<Int>): Int = sum(numbers) { true }

    fun sumAllEven(numbers: List<Int>): Int = sum(numbers) { it % 2 == 0 }

    fun sumAllOverThree(numbers: List<Int>): Int = sum(numbers) { it > 3 }

    private fun sum(numbers: List<Int>, needToAdd: (number: Int) -> Boolean): Int {
        return numbers.filter(needToAdd).sum()
    }
}
