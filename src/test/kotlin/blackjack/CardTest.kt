package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.CardType
import blackjack.domain.Denomination
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `CardType은 4가지(다이아몬드, 하트, 클로버, 스페이드)를 가진다`() {
        val cardTypeList = CardType.values()

        cardTypeList.size shouldBe 4
        cardTypeList shouldBe listOf(CardType.CLUBS, CardType.DIAMONDS, CardType.HEARTS, CardType.SPADES)
    }

    @Test
    fun `Denomintaion은 13종류(ACE ~ King)를 가진다`() {
        val denominationList = Denomination.values()

        denominationList shouldBe listOf(
            Denomination.ACE,
            Denomination.TWO,
            Denomination.THREE,
            Denomination.FOUR,
            Denomination.FIVE,
            Denomination.SIX,
            Denomination.SEVEN,
            Denomination.EIGHT,
            Denomination.NINE,
            Denomination.TEN,
            Denomination.JACK,
            Denomination.QUEEN,
            Denomination.KING,
        )
    }

    @Test
    fun `CardDeck은 다이아몬드, 하트, 클로버, 스페이드가 각각 13개의 끗수로 이루어져있다`() {
        val deck = CardDeck.cardDeck
        val validMap = mutableMapOf(*CardType.values().map { it to 0 }.toTypedArray())

        deck.forEach { card -> validMap[card.type] = validMap[card.type]!! + 1 }

        validMap.values shouldBe listOf(13, 13, 13, 13)
    }
}
