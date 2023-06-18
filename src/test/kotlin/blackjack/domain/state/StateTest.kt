package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.FinishState
import blackjack.domain.state.finish.Stay
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf

class StateTest : BehaviorSpec({

    Given(name = "Hit 생성할 수 있다.") {
        val hit = Hit(
            playingCards = PlayingCards(
                cards = mutableSetOf(
                    Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
                    Card(denomination = Denomination.KING, suit = Suit.CLUBS),
                ),
            ),
        )

        When(name = "draw 요청을 하게 되면") {
            val card = Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS)
            val actual = hit.draw(card = card)

            Then(name = "hit 상태로 유지하고 카드를 한 장 뽑는다.") {
                actual.shouldBeTypeOf<Hit>()
                actual.playingCards shouldBe hit.playingCards + card
            }
        }

        When(name = "Stay 요청을 하게 되면") {
            val actual = hit.stay()

            Then(name = "FinishState 상태로 변경한다.") {
                actual.shouldBeInstanceOf<FinishState>()
            }
        }
    }

    Given(name = "Blackjack 상태를 생성할 수 있다.") {
        val blackjack = Blackjack(playingCards = PlayingCards(cards = mutableSetOf()))

        When(name = "스코어 결과를 요청하게 되면") {
            val resultScore = blackjack.resultScore()

            Then(name = "블랙잭 점수인 21점을 반환한다.") {
                resultScore shouldBe 21
            }
        }
    }

    Given(name = "Stay 상태를 생성하는 카드가 주어진다.") {
        val expect21 = PlayingCards(
            cards = mutableSetOf(
                Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
                Card(denomination = Denomination.QUEEN, suit = Suit.DIAMONDS),
            ),
        )

        When(name = "스코어 결과를 요청하게 되면") {
            val blackjack = Stay(playingCards = expect21)
            val resultScore = blackjack.resultScore()

            Then(name = "에이스가 보너스 점수로 들어가 기대하는 21점을 반환한다.") {
                resultScore shouldBe 21
            }
        }

        val expect19 = PlayingCards(
            cards = mutableSetOf(
                Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
                Card(denomination = Denomination.QUEEN, suit = Suit.DIAMONDS),
                Card(denomination = Denomination.EIGHT, suit = Suit.DIAMONDS),
            ),
        )

        When(name = "스코어 결과를 요청하게 되면") {
            val blackjack = Stay(playingCards = expect19)
            val resultScore = blackjack.resultScore()

            Then(name = "에이스가 일반 점수로 들어가 기대하는 19점을 반환한다.") {
                resultScore shouldBe 19
            }
        }
    }
})
