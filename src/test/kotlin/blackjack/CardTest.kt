package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardType
import blackjack.domain.card.Denomination
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
    fun `ACE 카드는 1 또는 11 score를 가질 수 있다`() {
        val deck = CardDeck.cardDeck
        val aceCard = deck.first { it.denom.symbol == Denomination.ACE.symbol }

        with(aceCard.denom.cardScores.value) {
            this.size shouldBe 2
            this[0].value shouldBe 1
            this[1].value shouldBe 11
        }
    }
}
