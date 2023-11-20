package blackjack_dealer

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.CardNumber
import blackjack_dealer.entity.CardShape
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.GamerCurrentState
import io.kotest.core.spec.style.StringSpec

class ParticipantTest : StringSpec({
    CardDeque.create()
    val participant = Participant.newInstance("pita")

    "처음으로 생성한 참가자의 카드 숫자는 2개이다" {
        val expected = 2
        participant.getCurrentCardCount shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 HIT 이다" {
        val expected = GamerCurrentState.HIT
        participant.getCurrentState shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 운이 좋게도 BLACK_JACK 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.J, CardShape.CLOVER)))
        val participantWithBlackJack = Participant("pita", blackJackCards)
        val expected = GamerCurrentState.BLACKJACK
        participant.getCurrentState shouldBe expected
    }
})
