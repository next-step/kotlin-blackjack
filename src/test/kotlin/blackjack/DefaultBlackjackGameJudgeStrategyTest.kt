package blackjack

import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.bustCards
import blackjack.InitialCardsTestFixtures.initial19Cards
import blackjack.InitialCardsTestFixtures.initial20Cards
import blackjack.InitialCardsTestFixtures.initial21Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultBlackjackGameJudgeStrategyTest : BehaviorSpec({
    given("Dealer 가 Blackjack 일 때") {
        val dealerState = Blackjack(Cards(blackjackCards))
        val sut = DefaultBlackjackGameJudgeStrategy()

        `when`("Player 가 Stay 이면") {
            val playerState = Stay(Cards(initial21Cards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 LOSS 다") {
                result shouldBe PlayerResult.LOSS
            }
        }

        `when`("Player 가 Bust 이면") {
            val playerState = Bust(Cards(bustCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 LOSS 다") {
                result shouldBe PlayerResult.LOSS
            }
        }

        `when`("Player 가 Blackjack 이면") {
            val playerState = Blackjack(Cards(blackjackCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 PUSH 다") {
                result shouldBe PlayerResult.PUSH
            }
        }
    }

    given("Dealer 가 Bust 상태일 때") {
        val dealerState = Bust(Cards(bustCards))
        val sut = DefaultBlackjackGameJudgeStrategy()

        `when`("Player 가 Stay 이면") {
            val playerState = Stay(Cards(initial21Cards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 WIN 다") {
                result shouldBe PlayerResult.WIN
            }
        }

        `when`("Player 가 Bust 이면") {
            val playerState = Bust(Cards(bustCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 LOSS 다") {
                result shouldBe PlayerResult.LOSS
            }
        }

        `when`("Player 가 Blackjack 이면") {
            val playerState = Blackjack(Cards(blackjackCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 WIN 다") {
                result shouldBe PlayerResult.WIN
            }
        }
    }

    given("Dealer 가 Stay(20) 상태 일 때") {
        val dealerState = Stay(Cards(initial20Cards))
        val sut = DefaultBlackjackGameJudgeStrategy()

        `when`("Player 가 Bust 이면") {
            val playerState = Bust(Cards(bustCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 LOSS 다") {
                result shouldBe PlayerResult.LOSS
            }
        }

        `when`("Player 가 Blackjack 이면") {
            val playerState = Blackjack(Cards(blackjackCards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 WIN 다") {
                result shouldBe PlayerResult.WIN
            }
        }

        `when`("Player 가 Stay(19) 이면") {
            val playerState = Stay(Cards(initial19Cards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 LOSS 다") {
                result shouldBe PlayerResult.LOSS
            }
        }

        `when`("Player 가 Stay(21) 이면") {
            val playerState = Stay(Cards(initial21Cards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 WIN 다") {
                result shouldBe PlayerResult.WIN
            }
        }

        `when`("Player 가 Stay(20) 이면") {
            val playerState = Stay(Cards(initial20Cards))
            val result = sut.evaluatePlayerResult(dealerState, playerState)

            then("PlayerResult 는 PUSH 다") {
                result shouldBe PlayerResult.PUSH
            }
        }
    }
})
