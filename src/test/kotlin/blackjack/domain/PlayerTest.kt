package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("카드를 더 받는다(hit).") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val card = Card.of(Denomination.TWO, Suit.HEARTS)
        val player = Player("pobi", cards)

        player.hit(card)

        player.cards shouldBe Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )
        player.burst shouldBe false
    }

    test("카드를 더 받고(hit) 21 초과하면 burst 된다") {
        val cards = Cards(
            Card.of(Denomination.KING, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val card = Card.of(Denomination.TWO, Suit.HEARTS)
        val player = Player("pobi", cards)

        player.hit(card)

        player.cards shouldBe Cards(
            Card.of(Denomination.KING, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )
        player.burst shouldBe true
    }

    context("플레이어의 스코어를 계산한다.") {
        data class PlayerScore(val cards: Cards, val score: Int)
        withData(
            nameFn = { "${it.cards.joinToString(" + ") { card -> card.denomination.name }} = ${it.score}" },
            PlayerScore(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                    Card.of(Denomination.TWO, Suit.HEARTS),
                ),
                13
            ),
            PlayerScore(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                21
            )
        ) { (cards, score) ->
            val player = Player("pobi", cards)
            player.calculateScore() shouldBe score
        }
    }

    test("오픈한 카드를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )
        val player = Player("pobi", cards)

        player.openedCards() shouldBe cards
    }

    context("게임을 이겼는지 여부를 반환한다") {
        data class IsWinner(val cards: Cards, val isPlayerBurst: Boolean, val dealerScore: Int, val expected: Boolean)
        withData(
            IsWinner(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                21,
                true
            ),
            IsWinner(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                    Card.of(Denomination.ACE, Suit.HEARTS),
                ),
                false,
                21,
                false
            ),
            IsWinner(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                21,
                false
            ),
            IsWinner(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                22,
                true
            ),
            IsWinner(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                true,
                22,
                false
            ),
        ) { (cards, isPlayerBurst, dealerScore, expected) ->
            val player = Player("pobi", cards, isPlayerBurst)
            player.isWinner(dealerScore) shouldBe expected
        }
    }
})
