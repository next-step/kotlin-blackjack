package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Hand
import blackjack.domain.State
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class StateTestData(
    val hand: Hand,
    val expected: State
)

class StateTest : FunSpec({
    context("Dealer와 Player의 상태가 BLACKJACK") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.ACE, shape = CardShape.CLOVER),
                        Card(number = CardNumber.KING, shape = CardShape.CLOVER)
                    )
                )
            )
        ) { hand ->
            State.fromDealer(hand) shouldBe State.BLACKJACK
            State.fromPlayer(hand) shouldBe State.BLACKJACK
        }
    }

    context("Dealer와 Player의 상태가 BUST") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.FIVE, shape = CardShape.CLOVER),
                        Card(number = CardNumber.QUEEN, shape = CardShape.CLOVER),
                        Card(number = CardNumber.KING, shape = CardShape.CLOVER)
                    )
                ),
            )
        ) { hand ->
            State.fromDealer(hand) shouldBe State.BUST
            State.fromPlayer(hand) shouldBe State.BUST
        }
    }

    context("Dealer의 상태가 HIT") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.ACE, shape = CardShape.CLOVER),
                        Card(number = CardNumber.FIVE, shape = CardShape.CLOVER)
                    )
                ),
            )
        ) { hand ->
            State.fromDealer(hand) shouldBe State.HIT
        }
    }

    context("Player의 상태가 HIT") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.ACE, shape = CardShape.CLOVER),
                        Card(number = CardNumber.FIVE, shape = CardShape.CLOVER)
                    )
                ),
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.EIGHT, shape = CardShape.CLOVER),
                        Card(number = CardNumber.KING, shape = CardShape.CLOVER)
                    )
                ),
            )
        ) { hand ->
            State.fromPlayer(hand) shouldBe State.HIT
        }
    }

    context("Dealer의 상태가 STAY") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(number = CardNumber.EIGHT, shape = CardShape.CLOVER),
                        Card(number = CardNumber.KING, shape = CardShape.CLOVER)
                    )
                ),
            )
        ) { hand ->
            State.fromDealer(hand) shouldBe State.STAY
        }
    }
})
