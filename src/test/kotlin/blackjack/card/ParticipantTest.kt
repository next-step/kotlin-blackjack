package blackjack.card

import blackjack.entity.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import blackjack.entity.Participant
import blackjack.entity.ParticipantState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({
    "참여자가 가진 카드의 합이 21 이상인 경우 카드를 받을 수 없다" {
        val cardList = listOf(
            Card(CardNumber.J, CardShape.DIAMOND),
            Card(CardNumber.J, CardShape.HEART),
            Card(CardNumber.J, CardShape.CLOVER)
        )
        val participant = Participant("pita", Cards(cardList))
        participant.participantState shouldBe ParticipantState.BUST
    }

    "참여자가 가진 카드의 합이 21 미만인 경우 카드를 받을 수 있다" {
        val cardList = listOf(
            Card(CardNumber.THREE, CardShape.DIAMOND),
            Card(CardNumber.FOUR, CardShape.HEART),
        )
        val participant = Participant("pita", Cards(cardList))
        participant.participantState shouldBe ParticipantState.HIT
    }

    "참여자가 가진 카드의 합이 21이 넘고, A를 포함하는 경우 합을 계산할 때 A를 1로 계산하여 더 받을 수 있다고 본다" {
        // 카드의 합은 24 (A를 11로 계산시에)
        val cardList = listOf(
            Card(CardNumber.A, CardShape.DIAMOND),
            Card(CardNumber.J, CardShape.HEART),
            Card(CardNumber.THREE, CardShape.SPADE),
        )
        val participant = Participant("pita", Cards(cardList))
        participant.participantState shouldBe ParticipantState.HIT
    }

    "참여자가 가진 카드의 합이 21인 경우 블랙잭으로 본다" {
        // 카드의 합은 24 (A를 11로 계산시에)
        val cardList = listOf(
            Card(CardNumber.A, CardShape.DIAMOND),
            Card(CardNumber.J, CardShape.HEART),
        )
        val participant = Participant("pita", Cards(cardList))
        participant.participantState shouldBe ParticipantState.BLACKJACK
    }
})
