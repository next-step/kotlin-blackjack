package fp

fun List<Int>.sumAll() = sum()

fun List<Int>.sumAllEven() = filter { it % 2 == 0 }.sum()

fun List<Int>.sumAllOverThree() = filter { it > 3 }.sum()
