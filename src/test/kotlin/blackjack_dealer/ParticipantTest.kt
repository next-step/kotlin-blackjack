package blackjack_dealer

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.CardNumber
import blackjack_dealer.entity.CardShape
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.GamerCurrentState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({
    CardDeque.create()
    val participant = Participant.newInstance("pita")

    "생성한 이름이 잘 나온다" {
        val expected = "pita"
        participant.getParticipantName() shouldBe expected
    }

    "처음으로 생성한 참가자의 카드 숫자는 2개이다" {
        val expected = 2
        participant.getCurrentCards().count() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 HIT 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.CLOVER)))
        val participantWithHit = Participant.newInstance(name = "pita", cards = blackJackCards)
        val expected = GamerCurrentState.HIT
        participantWithHit.getCurrentState() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 운이 좋게도 BLACK_JACK 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.J, CardShape.CLOVER)))
        val participantWithBlackJack = Participant.newInstance(name = "pita", cards = blackJackCards)
        val expected = GamerCurrentState.BLACKJACK
        participantWithBlackJack.getCurrentState() shouldBe expected
    }
})
