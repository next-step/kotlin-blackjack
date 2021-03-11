package lambda

object Sum {
    fun sumAll(numbers: List<Int>): Int = sum(numbers) { true }

    fun sumAllEven(numbers: List<Int>): Int = sum(numbers) { it % 2 == 0 }

    fun sumAllOverThree(numbers: List<Int>): Int = sum(numbers) { it > 3 }

    fun sum(numbers: List<Int>, enableSum: (number: Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (enableSum(number)) {
                total += number
            }
        }
        return total
    }
}
