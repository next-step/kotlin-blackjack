package lambda

fun sum(numbers: List<Int>, condition: (number: Int) -> Boolean): Int {
    return numbers.filter(condition).sum()
}
