package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.state.ParticipantResultState
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ResultBoardTest : BehaviorSpec({
    // 승무패 확
    Given("딜러의 점수가 17점일 때") {
        val cards =
            mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
        val dealer = Dealer.newInstance(cards)
        When("참가자가 승인 경우 : 참가자 18") {
            val participantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.EIGHT, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "승"

            Then("결과는 승으로 나온다") {
                val totalResult = BlackJackResultBoard.getBlackJackResult(dealer, Participants(listOf(participant)))
                totalResult.participantsResult.first().resultState.state shouldBe expected
            }
        }

        When("참가자와 무승부인 경우 : 참가자 17") {
            val participantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "무"

            Then("결과는 무승부로 나온다") {
                val totalResult = BlackJackResultBoard.getBlackJackResult(dealer, Participants(listOf(participant)))
                totalResult.participantsResult.first().resultState.state shouldBe expected
            }
        }

        When("참가자가 패배인 경우 : 참가자 16") {
            val participantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SIX, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "패"

            Then("결과는 무승부로 나온다") {
                val totalResult = BlackJackResultBoard.getBlackJackResult(dealer, Participants(listOf(participant)))
                totalResult.participantsResult.first().resultState.state shouldBe expected
            }
        }

        When("참가자가 패배인 경우 : 참가자 24") {
            val participantCard =
                mutableListOf(
                    Card(CardNumber.J, CardShape.CLOVER),
                    Card(CardNumber.J, CardShape.HEART),
                    Card(CardNumber.FOUR, CardShape.CLOVER)
                ).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "패"
            Then("결과는 패로 나온다") {
                val totalResult = BlackJackResultBoard.getBlackJackResult(dealer, Participants(listOf(participant)))
                println(totalResult)
                totalResult.participantsResult.first().resultState.state shouldBe expected
            }
        }
    }

    Given("딜러가 21이 넘는다면") {
        val cards =
            mutableListOf(
                Card(CardNumber.J, CardShape.CLOVER),
                Card(CardNumber.SEVEN, CardShape.CLOVER),
                Card(CardNumber.Q, CardShape.CLOVER)
            ).toGamerCards()
        val dealer = Dealer.newInstance(cards)
        When("참가자의 카드 값과 상관없이") {
            // 승
            val winningParticipantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.EIGHT, CardShape.CLOVER)).toGamerCards()
            val winningParticipant = Participant.newInstance("pita", winningParticipantCard)
            // 무
            val drawParticipantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
            val drawParticipant = Participant.newInstance("pita", drawParticipantCard)
            // 패
            val losingParticipantCard =
                mutableListOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SIX, CardShape.CLOVER)).toGamerCards()
            val losingParticipant = Participant.newInstance("pita", losingParticipantCard)

            val expected = 3
            Then("승리로 기록된다.") {
                val totalResult = BlackJackResultBoard.getBlackJackResult(
                    dealer,
                    Participants(
                        listOf(
                            winningParticipant, losingParticipant, drawParticipant
                        )
                    )
                )
                totalResult.participantsResult.filter { it.resultState == ParticipantResultState.WIN }
                    .count() shouldBe expected
            }
        }
    }
})
