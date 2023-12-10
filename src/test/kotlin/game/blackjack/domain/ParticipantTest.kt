package game.blackjack.domain

import game.blackjack.domain.CardNumber.ACE
import game.blackjack.domain.CardNumber.KING
import game.blackjack.domain.CardShape.DIAMOND
import game.blackjack.domain.CardShape.HEART
import game.blackjack.domain.CardShape.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({

    "카드를 한 장 뽑으면, 카드 패의 총 개수는 1 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.handCards.size
        participant.drawCard(Card(ACE, SPADE))

        originSize + 1 shouldBe participant.handCards.size
    }

    "카드를 두 장 뽑으면, 카드 패의 총 개수는 2 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.handCards.size
        participant.drawCard(listOf(Card(ACE, SPADE), Card(ACE, HEART)))

        originSize + 2 shouldBe participant.handCards.size
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘으면 bust 여부를 true로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCard(listOf(Card(KING, DIAMOND), Card(KING, SPADE), Card(KING, HEART)))

        participant.isBust() shouldBe true
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘지 않으면 bust 여부를 false로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCard(listOf(Card(KING, DIAMOND)))

        participant.isBust() shouldBe false
    }
})
