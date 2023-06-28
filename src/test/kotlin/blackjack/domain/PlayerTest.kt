package blackjack.domain

import blackjack.fixture.player
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import java.math.BigDecimal

class PlayerTest : FunSpec({
    test("카드를 더 받는다(hit).") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val card = Card.of(Denomination.TWO, Suit.HEARTS)
        val player = player(cards = cards)

        player.hit(card)

        player.state should instanceOf<Running>()
        player.state.cards shouldBe cards + card
    }

    test("카드를 더 받고(hit) 21 초과하면 burst 된다") {
        val cards = Cards(
            Card.of(Denomination.KING, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val card = Card.of(Denomination.TWO, Suit.HEARTS)
        val player = player(cards = cards)

        player.hit(card)

        player.state should instanceOf<Burst>()
        player.state.cards shouldBe cards + card
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
            val player = player(cards = cards)
            player.calculateScore() shouldBe Score(score)
        }
    }

    test("오픈한 카드를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )
        val player = player(cards = cards)

        player.openedCards() shouldBe cards
    }

    context("게임 수익을 계산한다") {
        data class StateProfit(
            val playerState: State,
            val dealerState: State,
            val profit: Profit
        )
        withData(
            StateProfit(
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                Profit.ZERO,
            ),
            StateProfit(
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Profit(1500.0.toBigDecimal()),
            ),
            StateProfit(
                Stay(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES), Card.of(Denomination.ACE, Suit.HEARTS))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                Profit(BigDecimal(-1000)),
            ),
            StateProfit(
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                Profit(BigDecimal(-1000)),
            ),
            StateProfit(
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Burst(Cards(Card.of(Denomination.KING, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS), Card.of(Denomination.TWO, Suit.CLUBS))),
                Profit(BigDecimal(1000)),
            ),
            StateProfit(
                Burst(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Burst(Cards(Card.of(Denomination.KING, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS), Card.of(Denomination.TWO, Suit.CLUBS))),
                Profit(BigDecimal(-1000)),
            ),
        ) { (playerState, dealerState, profit) ->
            val player = player(state = playerState, betAmount = BigDecimal(1000))
            val dealer = Dealer(dealerState)

            player.calculateProfit(dealer) shouldBe profit
        }
    }

    test("플레이어의 상태가 끝났는지 반환한다") {
        val cards = Cards(Card.of(Denomination.KING, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS), Card.of(Denomination.TWO, Suit.CLUBS))
        val player = player(state = Burst(cards))

        player.isFinished() shouldBe true
    }
})
