package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards
import blackjack.domain.card.MockPlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class StateTest : BehaviorSpec({

    Given(name = "State 클래스는 sealed class로 제공된다.") {
        val stateKClass = State::class

        Then(name = "각 State 상태를 확장할 때 테스트 코드르 추가해야 한다.") {
            when (stateKClass) {
                BustState::class -> Unit
                FinishState::class -> Unit
                RunningState::class -> Unit
                StartState::class -> Unit
            }
        }
    }

    Given(name = "StartState 생성할 수 있다.") {
        val startState = StartState(
            playingCards = InitPlayingCards(
                cards = listOf(
                    Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
                    Card(denomination = Denomination.KING, suit = Suit.CLUBS),
                ),
            ),
        )

        When(name = "Hit 요청을 하게 되면") {
            val card = Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS)
            val actual = startState.hit(card = card)

            Then(name = "Running 상태로 변경하고 카드를 한 장 뽑는다.") {
                actual::class shouldBe RunningState::class
                actual.playingCards shouldBe startState.playingCards + card
            }
        }

        When(name = "Stay 요청을 하게 되면") {
            val actual = startState.stay()

            Then(name = "FinishState 상태로 변경한다.") {
                actual::class shouldBe FinishState::class
            }
        }

        When(name = "턴 진행 가능 여부를 요청을 하게 되면") {
            val actual = startState.availableTurn()

            Then(name = "가능하다를 반환한다.") {
                actual shouldBe true
            }
        }

        When(name = "스코어 결과를 요청하게 되면") {
            val exception = shouldThrow<IllegalStateException> { startState.resultScore() }

            Then(name = "시작 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(startState))
            }
        }
    }

    Given(name = "RunningState 생성할 수 있다.") {
        val runningState = RunningState(
            playingCards = MockPlayingCards(
                cards = mutableSetOf(
                    Card(denomination = Denomination.TWO, suit = Suit.CLUBS),
                    Card(denomination = Denomination.KING, suit = Suit.CLUBS),
                ),
            ),
        )

        When(name = "Stay 요청을 하게 되면") {
            val actual = runningState.stay()

            Then(name = "FinishState 상태로 변경한다.") {
                actual::class shouldBe FinishState::class
            }
        }

        When(name = "턴 진행 가능 여부를 요청을 하게 되면") {
            val actual = runningState.availableTurn()

            Then(name = "가능하다를 반환한다.") {
                actual shouldBe true
            }
        }

        When(name = "Hit 요청을 하게 되면") {
            val card = Card(denomination = Denomination.FIVE, suit = Suit.CLUBS)
            val actual = runningState.hit(card = card)

            Then(name = "Running 상태로 변경하고 카드를 한 장 뽑는다.") {
                actual.playingCards shouldBe runningState.playingCards + card
            }

            val bustState = runningState.hit(card = Card(denomination = Denomination.FIVE, suit = Suit.DIAMONDS))

            Then(name = "21이 넘는 카드를 뽑았을 경우 Bust 상태가 반환된다.") {
                bustState::class shouldBe BustState::class
            }
        }

        When(name = "스코어 결과를 요청하게 되면") {
            val exception = shouldThrow<IllegalStateException> { runningState.resultScore() }

            Then(name = "시작 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(runningState))
            }
        }
    }

    Given(name = "BustState 생성할 수 있다.") {
        val card = Card(denomination = Denomination.FIVE, suit = Suit.CLUBS)

        val bustState = BustState(
            playingCards = MockPlayingCards(
                cards = mutableSetOf(
                    Card(denomination = Denomination.TWO, suit = Suit.CLUBS),
                    Card(denomination = Denomination.KING, suit = Suit.CLUBS),
                ),
            ),
        )

        When(name = "Hit 요청을 하게 되면") {
            val exception = shouldThrow<IllegalStateException> { bustState.hit(card = card) }

            Then(name = "버스트 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(bustState))
            }
        }

        When(name = "Stay 요청을 하게 되면") {
            val exception = shouldThrow<IllegalStateException> { bustState.stay() }

            Then(name = "버스트 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(bustState))
            }
        }

        When(name = "턴 진행 가능 여부를 요청을 하게 되면") {
            val actual = bustState.availableTurn()

            Then(name = "불가능하다를 반환한다.") {
                actual shouldBe false
            }
        }

        When(name = "스코어 결과를 요청하게 되면") {
            val resultScore = bustState.resultScore()

            Then(name = "버스트되었기에 0을 반환한다.") {
                resultScore shouldBe 0
            }
        }
    }

    Given(name = "FinishState 생성할 수 있다.") {
        val card = Card(denomination = Denomination.TWO, suit = Suit.CLUBS)
        val expect = mutableSetOf(card, Card(denomination = Denomination.KING, suit = Suit.CLUBS))

        val finishState = FinishState(
            playingCards = MockPlayingCards(
                cards = expect,
            ),
        )

        When(name = "Hit 요청을 하게 되면") {
            val exception = shouldThrow<IllegalStateException> { finishState.hit(card = card) }

            Then(name = "버스트 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(finishState))
            }
        }

        When(name = "Stay 요청을 하게 되면") {
            val exception = shouldThrow<IllegalStateException> { finishState.stay() }

            Then(name = "버스트 상태에서는 사용할 수 없다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(arrayOf(finishState))
            }
        }

        When(name = "턴 진행 가능 여부를 요청을 하게 되면") {
            val actual = finishState.availableTurn()

            Then(name = "불가능하다를 반환한다.") {
                actual shouldBe false
            }
        }

        When(name = "스코어 결과를 요청하게 되면") {
            val resultScore = finishState.resultScore()

            Then(name = "보너스 점수가 있지 않은 카드일 경우 최소 점수를 더해서 반환한다.") {
                resultScore shouldBe expect.sumOf { it.denomination.getMinimumScore() }
            }
        }
    }
})
