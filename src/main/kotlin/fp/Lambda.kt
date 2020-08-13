package fp

fun sumAll(numbers: List<Int>): Int = sum(numbers) { true }

fun sumAllEven(numbers: List<Int>): Int = sum(numbers) { number -> number % 2 == 0 }

fun sumAllOverThree(numbers: List<Int>): Int = sum(numbers) { number -> number > 3 }

fun sum(numbers: List<Int>, isAdd: (Int) -> Boolean): Int = numbers.filter { isAdd(it) }.sum()
