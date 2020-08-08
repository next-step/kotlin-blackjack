package lambda

fun List<Int>.sumAll() = sumBy { true }

fun List<Int>.sumAllEven() = sumBy { it % 2 == 0 }

fun List<Int>.sumAllOverThree() = sumBy { it > 3 }

private fun List<Int>.sumBy(condition: (number: Int) -> Boolean) = this.filter(condition).sum()
