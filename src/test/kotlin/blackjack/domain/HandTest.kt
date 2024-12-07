package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    fun Hand.addCards(vararg cards: Card) {
        cards.forEach { card ->
            this.addCard(card)
        }
    }

    "카드를 추가할 수 있다." {
        val hand = Hand()
        val beforeSize = hand.getAllCards().size
        hand.addCard(Card(Rank.ACE, Suit.HEARTS))
        val afterSize = hand.getAllCards().size

        afterSize shouldBe beforeSize + 1
    }

    "카드의 합을 계산할 수 있다." {
        forAll(
            row(Card(Rank.ACE, Suit.HEARTS), Card(Rank.TWO, Suit.DIAMONDS), 13),
            row(Card(Rank.TWO, Suit.HEARTS), Card(Rank.KING, Suit.SPADES), 12),
            row(Card(Rank.KING, Suit.HEARTS), Card(Rank.NINE, Suit.CLOVERS), 19),
            row(Card(Rank.JACK, Suit.HEARTS), Card(Rank.KING, Suit.HEARTS), 20),
            row(Card(Rank.QUEEN, Suit.HEARTS), Card(Rank.FIVE, Suit.HEARTS), 15),
            row(Card(Rank.ACE, Suit.HEARTS), Card(Rank.ACE, Suit.HEARTS), 12),
        ) { firstCard, secondCard, expected ->
            val hand = Hand()
            hand.addCards(firstCard, secondCard)
            hand.calculateBestTotal() shouldBe expected
        }
    }

    "카드의 합이 21을 초과하면 0을 반환한다." {
        forAll(
            row(Card(Rank.JACK, Suit.HEARTS), Card(Rank.KING, Suit.HEARTS), Card(Rank.QUEEN, Suit.CLOVERS)),
        ) { firstCard, secondCard, thirdCard ->
            val hand = Hand()
            hand.addCards(firstCard, secondCard, thirdCard)
            hand.calculateBestTotal() shouldBe 0
        }
    }
})
