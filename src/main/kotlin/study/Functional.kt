package study

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

fun sumAll(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        total += number
    }
    return total
}

fun sumAllLambda(numbers: List<Int>): Int {
    return numbers.fold(0) { acc, i -> acc + i }
}

fun sumAllEven(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number % 2 == 0) {
            total += number
        }
    }
    return total
}

fun sumAllEvenLambda(numbers: List<Int>): Int {
    return numbers.filter { it % 2 == 0 }.fold(0) { acc, i -> acc + i }
}

fun sumAllOverThree(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number > 3) {
            total += number
        }
    }
    return total
}

fun sumAllOverThreeLambda(numbers: List<Int>): Int {
    return numbers.filter { it > 3 }.fold(0) { acc, i -> acc + i }
}