package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    test("카드를 더한다.") {
        val cards = Cards(Card.of(Denomination.ACE, Suit.SPADES))
        val card = Card.of(Denomination.JACK, Suit.SPADES)

        cards + card shouldBe Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
    }

    test("Ace 카드 개수를 구한다.") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.ACE, Suit.HEARTS),
            Card.of(Denomination.JACK, Suit.CLUBS)
        )

        cards.countAces() shouldBe 2
    }

    context("카드들의 스코어를 계산한다.") {
        data class CardsScore(val cards: Cards, val score: Int)
        withData(
            nameFn = { "${it.cards.joinToString(" + ") { card -> card.denomination.name }} = ${it.score}" },
            CardsScore(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                    Card.of(Denomination.TWO, Suit.HEARTS),
                ),
                13
            ),
            CardsScore(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                21
            )
        ) { (cards, score) ->
            cards.calculateScore() shouldBe score
        }
    }
})
