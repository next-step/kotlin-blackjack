package blackjack.card

import blackjack.entity.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import blackjack.entity.Participant
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
        participant.canGetCard shouldBe false
    }

    "참여자가 가진 카드의 합이 21 미만인 경우 카드를 받을 수 있다" {
        val cardList = listOf(
            Card(CardNumber.THREE, CardShape.DIAMOND),
            Card(CardNumber.FOUR, CardShape.HEART),
        )
        val participant = Participant("pita", Cards(cardList))
        participant.canGetCard shouldBe true
    }
})
