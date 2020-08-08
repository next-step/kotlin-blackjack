package fp

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

fun sumAll(numbers: List<Int>): Int = sum(numbers) { true }

fun sumAllEven(numbers: List<Int>): Int = sum(numbers) { it % 2 == 0 }

fun sumAllOverThree(numbers: List<Int>): Int = sum(numbers) { it > 3 }

fun sum(numbers: List<Int>, isCheck: (Int) -> Boolean): Int {
    var total = 0
    for (number in numbers) {
        if (isCheck(number)) {
            total += number
        }
    }
    return total
}
