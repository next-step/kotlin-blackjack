package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.MockPlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

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
        val runningState = RunningState(
            playingCards = MockPlayingCards(
                cards = mutableSetOf(
                    Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
                    Card(denomination = Denomination.KING, suit = Suit.CLUBS),
                ),
            ),
        )

        When(name = "Hit 요청을 하게 되면") {
            val card = Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS)
            val actual = runningState.hit(card = card)

            Then(name = "Running 상태로 변경하고 카드를 한 장 뽑는다.") {
                actual::class shouldBe RunningState::class
                actual.playingCards shouldBe runningState.playingCards + card
            }
        }

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
    }

    Given(name = "RunningState 생성할 수 있다.") {
        val stateKClass = State::class

        When(name = "Hit 요청을 하게 되면") {
            Then(name = "Running 상태로 변경하고 카드를 한 장 뽑는다.") {
            }
        }

        When(name = "Stay 요청을 하게 되면") {
            Then(name = "FinishState 상태로 변경한다.") {
            }
        }

        When(name = "턴 진행 가능 여부를 요청을 하게 되면") {
            Then(name = "가능하다를 반환한다.") {
            }
        }
    }
})
