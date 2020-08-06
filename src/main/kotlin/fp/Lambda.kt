package fp

class Lambda {
    private fun sumIf(numbers: List<Int>, predicate : (Int)->Boolean) : Int {
        return numbers.filter { predicate(it) }.sum()
    }

    fun sumAll(numbers: List<Int>): Int {
        return sumIf(numbers) { true }
    }

    fun sumAllEven(numbers: List<Int>): Int {
        return sumIf(numbers) { it % 2 == 0 }
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        return sumIf(numbers) { it > 3 }
    }
}
