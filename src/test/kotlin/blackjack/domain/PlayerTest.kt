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

    context("게임 결과를 반환한다") {
        data class GetGameResult(
            val cards: Cards,
            val isPlayerBurst: Boolean,
            val dealerScore: Int,
            val gameResult: GameResult
        )
        withData(
            GetGameResult(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                21,
                GameResult.TIE
            ),
            GetGameResult(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                    Card.of(Denomination.ACE, Suit.HEARTS),
                ),
                false,
                21,
                GameResult.LOSE
            ),
            GetGameResult(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                21,
                GameResult.LOSE
            ),
            GetGameResult(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                false,
                22,
                GameResult.WIN
            ),
            GetGameResult(
                Cards(
                    Card.of(Denomination.KING, Suit.SPADES),
                    Card.of(Denomination.JACK, Suit.SPADES),
                ),
                true,
                22,
                GameResult.LOSE
            ),
        ) { (cards, isPlayerBurst, dealerScore, gameResult) ->
            val player = Player("pobi", cards, isPlayerBurst)
            player.getGameResult(dealerScore) shouldBe gameResult
        }
    }
})
