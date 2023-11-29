package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardPattern
import blackjack.domain.CardShuffler
import blackjack.domain.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "카드를 한 장 뽑을 수 있다." {
        // given
        val deck = Deck(
            shuffler = createShuffler()
        )

        // when
        val card = deck.draw()

        // then
        card.number shouldBe CardNumber.ACE
        card.pattern shouldBe CardPattern.HEART
    }
})

private fun createShuffler(): CardShuffler {
    return object : CardShuffler {
        override fun shuffle(cards: List<Card>): List<Card> {
            return cards
        }
    }
}
