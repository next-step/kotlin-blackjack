package blackjack_dealer

import blackjack_dealer.entity.AllParticipantWithBetAmount
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : StringSpec({
    "전달한 이름의 수만큼 참가자를 생성했다" {
        val input = "pita,haero,sery"
        val expected = 3
        CardDeque().create()
        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1, 2, 3)
        )
        Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = { listOf(Card(CardNumber.TWO, CardShape.CLOVER)).toGamerCards() },
        ).size shouldBe expected
    }
})
