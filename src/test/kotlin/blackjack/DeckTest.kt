package blackjack

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "빈 덱은 생성한다." {
        shouldThrow<IllegalArgumentException> {
            Deck()
        }
    }

    " 스페이스 A 를 덱에 넣고 카드를 가져온다" {
        val card = Card(Suit.SPADE, Number.ACE)
        val deck = Deck(cards = mutableListOf<Card>().apply {
            add(card)
        })
        val draw = deck.draw()
        draw.number shouldBe Number.ACE
        draw.suit shouldBe Suit.SPADE
    }

})
