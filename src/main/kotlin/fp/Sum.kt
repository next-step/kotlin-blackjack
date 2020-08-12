package fp

fun sumAll(numbers: List<Int>) = numbers.sum()

fun sumAllEven(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.sum()

fun sumAllOverThree(numbers: List<Int>) = numbers.filter { it > 3 }.sum()
