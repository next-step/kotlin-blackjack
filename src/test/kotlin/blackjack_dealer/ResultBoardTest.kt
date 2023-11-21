package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardNumber
import blackjack_dealer.entity.CardShape
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.BehaviorSpec

class ResultBoardTest : BehaviorSpec({
    // 승무패 확
    Given("딜러의 점수가 17점일 때") {
        val cards =
            listOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
        val dealer = Dealer.newInstance(cards)
        When("참가자가 승인 경우 : 참가자 18") {
            val participantCard =
                listOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.EIGHT, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "승"

            Then("결과는 승으로 나온다") {
                val result = ResultBoard.calculateResult(dealer, participant)
                result 어쩌구 ~ 는 승
            }
        }

        When("참가자와 무승부인 경우 : 참가자 17") {
            val participantCard =
                listOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "무승부"

            Then("결과는 무승부로 나온다") {
                val result = ResultBoard.calculateResult(dealer, participant)
                result 어쩌구 ~ 는 무승부
            }
        }

        When("참가자가 패배인 경우 : 참가자 16") {
            val participantCard =
                listOf(Card(CardNumber.J, CardShape.CLOVER), Card(CardNumber.SIX, CardShape.CLOVER)).toGamerCards()
            val participant = Participant.newInstance("pita", participantCard)
            val expected = "패"

            Then("결과는 무승부로 나온다") {
                val result = ResultBoard.calculateResult(dealer, participant)
                result 어쩌구 ~ 는 패
            }
        }
    }
})
