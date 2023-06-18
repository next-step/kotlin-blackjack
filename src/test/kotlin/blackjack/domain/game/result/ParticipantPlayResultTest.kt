package blackjack.domain.game.result

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.mockPlayer
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.Bust
import blackjack.domain.state.finish.Stay
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ParticipantPlayResultTest : BehaviorSpec({

    Given(name = "참가자 플레이 결과는 다른 플레이 결과와 비교를 제공하며") {
        val otherBlackJack = Blackjack(playingCards = PlayingCards(cards = mutableSetOf()))

        val blackjack = ParticipantPlayResult(
            participant = mockPlayer(name = "진원"),
            finishState = otherBlackJack,
        )

        When(name = "참가자의 플레이 결과가 블랙잭이면") {
            val actual = blackjack.isWinner(otherFinishState = otherBlackJack)

            Then(name = "무조건 승리한다.") {
                actual shouldBe true
            }
        }

        val otherBust = Bust(playingCards = PlayingCards(cards = mutableSetOf()))

        val bust = ParticipantPlayResult(
            participant = mockPlayer(name = "진원"),
            finishState = otherBust,
        )

        When(name = "참가자의 플레이 결과가 버스트면") {
            val actual = bust.isWinner(otherFinishState = otherBlackJack)

            Then(name = "무조건 패배한다.") {
                actual shouldBe false
            }
        }

        val stay = ParticipantPlayResult(
            participant = mockPlayer(name = "진원"),
            finishState = Stay(
                playingCards = PlayingCards(
                    cards = mutableSetOf(
                        Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
                        Card(denomination = Denomination.NINE, suit = Suit.DIAMONDS),
                    ),
                ),
            ),
        )

        When(name = "참가자의 플레이 결과가 작은 스테이이면") {
            val actual = stay.isWinner(otherFinishState = otherBlackJack)

            Then(name = "패배한다.") {
                actual shouldBe false
            }
        }

        When(name = "참가자의 플레이 결과가 큰 스테이이면") {
            val actual = stay.isWinner(otherFinishState = otherBust)

            Then(name = "패배한다.") {
                actual shouldBe true
            }
        }
    }
})
