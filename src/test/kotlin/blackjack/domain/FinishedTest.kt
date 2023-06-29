package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FinishedTest : FunSpec({

    test("끝난 상태에서 hit 을 하면 예외가 발생한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )

        val exception = shouldThrow<IllegalStateException> {
            val stay = Stay(cards)

            stay.hit(Card.of(Denomination.THREE, Suit.HEARTS))
        }

        exception.message shouldBe "끝난 상태에서 hit 할 수 없습니다."
    }

    test("끝난 상태에서 stay 을 하면 예외가 발생한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )

        val exception = shouldThrow<IllegalStateException> {
            val stay = Stay(cards)

            stay.stay()
        }

        exception.message shouldBe "끝난 상태에서 stay 할 수 없습니다."
    }

    context("게임 결과를 반환한다") {
        data class StateGameResult(
            val playerState: State,
            val dealerState: State,
            val gameResult: GameResult
        )
        withData(
            StateGameResult(
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                GameResult.TIE,
            ),
            StateGameResult(
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                GameResult.WIN,
            ),
            StateGameResult(
                Stay(Cards(Card.of(Denomination.ACE, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES), Card.of(Denomination.ACE, Suit.HEARTS))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                GameResult.LOSE,
            ),
            StateGameResult(
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                BlackJack(Cards(Card.of(Denomination.ACE, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS))),
                GameResult.LOSE,
            ),
            StateGameResult(
                Stay(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Burst(Cards(Card.of(Denomination.KING, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS), Card.of(Denomination.TWO, Suit.CLUBS))),
                GameResult.WIN,
            ),
            StateGameResult(
                Burst(Cards(Card.of(Denomination.KING, Suit.SPADES), Card.of(Denomination.JACK, Suit.SPADES))),
                Burst(Cards(Card.of(Denomination.KING, Suit.CLUBS), Card.of(Denomination.JACK, Suit.CLUBS), Card.of(Denomination.TWO, Suit.CLUBS))),
                GameResult.LOSE,
            ),
        ) { (playerState, dealerState, gameResult) ->
            playerState.gameResult(dealerState) shouldBe gameResult
        }
    }
})
