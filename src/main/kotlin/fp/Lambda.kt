package fp

class Lambda {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumAll(numbers: List<Int>): Int {
        return numbers.sum().toInt()
    }

    fun sumAllEven(numbers: List<Int>): Int {
        return numbers.sumBy { if (it % 2 == 0) it else 0 }.toInt()
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        return numbers.sumBy { if (it > 3) it else 0 }.toInt()
    }
}
