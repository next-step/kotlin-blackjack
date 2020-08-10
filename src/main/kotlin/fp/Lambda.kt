package fp

typealias Numbers = List<Int>

val numbers: Numbers = listOf(1, 2, 3, 4, 5, 6)

fun sumAll(numbers: Numbers): Int = sum(numbers) { true }

fun sumAllEven(numbers: Numbers): Int =
    sum(numbers) { it % 2 == 0 }

fun sumAllOverThree(numbers: Numbers): Int = sum(numbers) { it > 3 }

fun sum(numbers: Numbers, predicate: (Int) -> (Boolean)): Int {
    return numbers.filter { predicate(it) }.sum()
}
