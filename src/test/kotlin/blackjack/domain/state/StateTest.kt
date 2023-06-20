package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.Stay
import blackjack.domain.state.running.Hit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf

class StateTest : BehaviorSpec({

    Given(name = "Hit 상태인 경우에") {
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

    Given(name = "Blackjack 상태인 경우에") {
        val blackjack = Blackjack(playingCards = PlayingCards(cards = mutableSetOf()))

        When(name = "스코어 결과를 요청하게 되면") {
            val resultScore = blackjack.resultScore()

            Then(name = "블랙잭 점수인 21점을 반환한다.") {
                resultScore shouldBe 21
            }
        }

        When(name = "배팅 금액 이익금에 대해 요청하게 되면") {
            val betAmount = 1000.0

            val tieProfit = blackjack.profit(betAmount = betAmount, otherState = mockBlackjackState)

            Then(name = "상대방도 블랙잭인 경우 무승부로 이익금이 없다.") {
                tieProfit shouldBe betAmount * 0.0
            }

            val bustProfit = blackjack.profit(betAmount = betAmount, otherState = mockBustState)
            val stayProfit = blackjack.profit(betAmount = betAmount, otherState = mockHitState.stay())

            Then(name = "상대방이 블랙잭이 아닌 경우 항상 1.5배의 이익금을 얻는다.") {
                bustProfit shouldBe betAmount * 1.5
                stayProfit shouldBe betAmount * 1.5
            }
        }
    }

    Given(name = "Bust 상태인 경우에") {
        val bust = mockBustState

        When(name = "스코어 결과를 요청하게 되면") {
            val resultScore = bust.resultScore()

            Then(name = "버스트 점수인 0점을 반환한다.") {
                resultScore shouldBe 0
            }
        }

        When(name = "배팅 금액 이익금에 대해 요청하게 되면") {
            val betAmount = 1000.0

            val blackjackProfit = bust.profit(betAmount = betAmount, otherState = mockBlackjackState)
            val bustProfit = bust.profit(betAmount = betAmount, otherState = mockBustState)
            val stayProfit = bust.profit(betAmount = betAmount, otherState = mockHitState.stay())

            Then(name = "어떤 상대라도 배팅 금액을 잃는다.") {
                blackjackProfit shouldBe betAmount * -1.0
                bustProfit shouldBe betAmount * -1.0
                stayProfit shouldBe betAmount * -1.0
            }
        }
    }

    Given(name = "Stay 상태인 경우에") {
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

        When(name = "배팅 금액 이익금에 대해 요청하게 되면") {
            val betAmount = 1000.0

            val largeStay = Stay(playingCards = expect21)
            val smallStay = Stay(playingCards = expect19)

            val smallProfit = smallStay.profit(betAmount = betAmount, otherState = largeStay)
            val blackjackProfit = smallStay.profit(betAmount = betAmount, otherState = mockBlackjackState)

            Then(name = "자기보다 크거나 상대방이 블랙잭인 경우 배팅금을 잃는다.") {
                blackjackProfit shouldBe betAmount * -1.0
                smallProfit shouldBe betAmount * -1.0
            }

            val largeProfit = largeStay.profit(betAmount = betAmount, otherState = smallStay)
            val bustProfit = smallStay.profit(betAmount = betAmount, otherState = mockBustState)

            Then(name = "자기보다 작거나 상대방이 버스트인 경우 배팅금을 얻는다.") {
                largeProfit shouldBe betAmount * 1.0
                bustProfit shouldBe betAmount * 1.0
            }
        }
    }
})
