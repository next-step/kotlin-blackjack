package fp

class Lambda {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumAll(numbers: List<Int>): Int = sumIf(numbers) { true }

    fun sumAllEven(numbers: List<Int>): Int = sumIf(numbers) {
        it % 2 == 0
    }

    fun sumAllOverThree(numbers: List<Int>): Int = sumIf(numbers) {
        it > 3
    }

    private fun sumIf(numbers: List<Int>, ifCondition: (num: Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (ifCondition(number)) {
                total += number
            }
        }
        return total
    }
}
