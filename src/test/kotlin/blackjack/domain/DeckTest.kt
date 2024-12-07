package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "Deck에서 카드를 뽑는다." {
        val sut = Deck(
            listOf(
                Card(CardSuit.CLUBS, CardNumber.ACE),
                Card(CardSuit.HEARTS, CardNumber.TWO),
                Card(CardSuit.DIAMONDS, CardNumber.THREE),
                Card(CardSuit.SPADES, CardNumber.FOUR),
            )
        )

        val actual = sut.draw()

        actual.toDrawCard() shouldBe DrawCard(CardSuit.CLUBS, CardNumber.ACE)
    }
})
