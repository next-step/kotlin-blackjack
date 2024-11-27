package blackjack

sealed class Rank(val value: Int, val displayName: String) {
    data object ACE : Rank(1, "A") {
        fun getPossibleValues(): List<Int> = listOf(1, 11)
    }

    class Number(value: Int, displayName: String) : Rank(value, displayName)

    companion object {
        val TWO = Number(2, "2")
        val THREE = Number(3, "3")
        val FOUR = Number(4, "4")
        val FIVE = Number(5, "5")
        val SIX = Number(6, "6")
        val SEVEN = Number(7, "7")
        val EIGHT = Number(8, "8")
        val NINE = Number(9, "9")
        val TEN = Number(10, "10")
        val JACK = Number(10, "J")
        val QUEEN = Number(10, "Q")
        val KING = Number(10, "K")
    }
}
