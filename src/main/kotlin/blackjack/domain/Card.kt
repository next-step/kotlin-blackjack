package blackjack.domain

class Card private constructor(
    val denomination: Denomination,
    private val suit: Suit
) {

    override fun toString(): String {
        return "${denomination.displayedName}${suit.type}"
    }

    companion object {
        private val availableCard: Map<Pair<Denomination, Suit>, Card> = init()
        private fun init(): Map<Pair<Denomination, Suit>, Card> {
            val map = mutableMapOf<Pair<Denomination, Suit>, Card>()
            Denomination.values().forEach { denomination ->
                Suit.values().forEach { suit ->
                    map[denomination to suit] = Card(denomination, suit)
                }
            }
            return map.toMap()
        }

        fun from(denomination: Denomination, suit: Suit): Card {
            return availableCard[denomination to suit] ?: throw IllegalArgumentException()
        }
    }
}

enum class Suit(
    val type: String
) {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아")
}

enum class Denomination(
    val minValue: Int,
    val maxValue: Int,
    val displayedName: String
) {
    ACE(1, 11, "1"),
    TWO(2, 2, "2"),
    THREE(3, 3, "3"),
    FOUR(4, 4, "4"),
    FIVE(5, 5, "5"),
    SIX(6, 6, "6"),
    SEVEN(7, 7, "7"),
    EIGHT(8, 8, "8"),
    NINE(9, 9, "9"),
    TEN(10, 10, "10"),
    JACK(10, 10, "J"),
    QUEEN(10, 10, "Q"),
    KING(10, 10, "K");
}
