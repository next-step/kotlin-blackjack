package fp

class RemoveRedundant {

    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumAll(numbers: List<Int>) = numbers.fold(0) { acc: Int, number: Int -> acc + number }

    fun sumAllEven(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.reduce { acc, i -> acc + i }

    fun sumAllOverThree(numbers: List<Int>) = numbers.filter { it > 3 }.reduce { acc: Int, number: Int -> acc + number }
}
