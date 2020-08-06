package fp

class Lambda {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumAll(numbers: List<Int>, isInCondition: (Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (isInCondition(number)) {
                total += number
            }
        }
        return total
    }

    fun sumAllEven(numbers: List<Int>, inCondition: (Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (inCondition(number)) {
                total += number
            }
        }
        return total
    }

    fun sumAllOverThree(numbers: List<Int>, inCondition: (Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (inCondition(number)) {
                total += number
            }
        }
        return total
    }
}
