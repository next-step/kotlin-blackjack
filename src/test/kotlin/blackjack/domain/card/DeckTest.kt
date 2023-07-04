package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "카드를 덱에 추가할 수 있다." {
        val deck = Deck()
        val card = Card(Denomination.ACE, Suit.SPADES)

        deck.add(card)

        deck.contains(card) shouldBe true
    }

    "덱에서 카드 점수를 반환한다." {
        val deck = Deck()
        val card1 = Card(Denomination.ACE, Suit.SPADES)
        val card2 = Card(Denomination.TWO, Suit.CLUBS)
        val card3 = Card(Denomination.THREE, Suit.HEARTS)

        deck.add(card1)
        deck.add(card2)
        deck.add(card3)

        val score = deck.getScore()

        score shouldBe 16
    }
})
