package blackjack

data class Point(val value: Int) {

    operator fun plus(adder: Point): Point {
        return Point(value + adder.value)
    }
    operator fun minus(adder: Point): Point {
        return Point(value - adder.value)
    }

    operator fun compareTo(compared: Point): Int {
        return this.value - compared.value
    }

    companion object{
        val ZERO = Point(0)
        val ACE = Point(1)
        val SPECIAL_ACE_USABLE_BOUNDARY = Point(10)
        val SPECIAL_ACE = Point(11)
        val MAX = Point(21)
    }
}
