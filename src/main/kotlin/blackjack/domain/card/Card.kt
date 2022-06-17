package blackjack.domain.card

data class Card(
    val suit: Suit,
    val denomination: Denomination
)

fun card(block: CardBuilder.() -> Unit): Card {
    return CardBuilder().apply(block).build()
}

class CardBuilder {
    private lateinit var suit: Suit
    private lateinit var denomination: Denomination

    infix fun String.to(value: String) {
        suit = suit(this)
        denomination = denomination(value)
    }

    infix fun String.to(value: Int) {
        suit = suit(this)
        denomination = NumberCard(value)
    }

    private fun suit(value: String): Suit {
        return when (value) {
            "다이아몬드" -> Suit.DIAMOND
            "하트" -> Suit.HEART
            "클로버" -> Suit.CLOVER
            "스페이드" -> Suit.SPADE
            else -> throw IllegalArgumentException("존재하지 않는 문양입니다")
        }
    }

    private fun denomination(value: String): Denomination {
        return when (value) {
            "A" -> Ace()
            "Q" -> Queen()
            "J" -> Jack()
            "K" -> King()
            else -> throw IllegalArgumentException("존재하지 않는 denomination 입니다")
        }
    }

    fun build(): Card {
        return Card(suit, denomination)
    }
}
