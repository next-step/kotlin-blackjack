package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드 만들기`() {
        val card = Card(Denomination.ACE to Shape.CLUB)

        assertThat(card.toString()).isEqualTo("A클로버")
    }
}

class Card(private val denominationToShape: Pair<Denomination, Shape>) {

    override fun toString(): String = "${denominationToShape.first.symbol}${denominationToShape.second.symbol}"
}

enum class Denomination(val symbol: String, val point: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10)
}

enum class Shape(val symbol: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아"),
    CLUB("클로버")
}
