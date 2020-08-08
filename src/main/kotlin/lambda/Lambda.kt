package lambda

fun sum(numbers: List<Int>, f: (number: Int) -> Boolean): Int {
    var total = 0
    for (number in numbers) {
        if (f(number)) {
            total += number
        }
    }
    return total
}
