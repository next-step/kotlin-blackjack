package fp

class Lambda {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumInCondition(numbers: List<Int>, condition: (Int) -> Boolean) =
        numbers.filter(condition).sum()
}
