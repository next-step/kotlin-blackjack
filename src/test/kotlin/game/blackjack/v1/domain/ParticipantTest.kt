package game.blackjack.v1.domain

import game.blackjack.v1.domain.CardNumber.ACE
import game.blackjack.v1.domain.CardNumber.EIGHT
import game.blackjack.v1.domain.CardNumber.FIVE
import game.blackjack.v1.domain.CardNumber.FOUR
import game.blackjack.v1.domain.CardNumber.JACK
import game.blackjack.v1.domain.CardNumber.KING
import game.blackjack.v1.domain.CardNumber.QUEEN
import game.blackjack.v1.domain.CardNumber.SIX
import game.blackjack.v1.domain.CardNumber.THREE
import game.blackjack.v1.domain.CardNumber.TWO
import game.blackjack.v1.domain.CardShape.DIAMOND
import game.blackjack.v1.domain.CardShape.HEART
import game.blackjack.v1.domain.CardShape.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({

    "카드를 한 장 뽑으면, 카드 패의 총 개수는 1 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.getHandSize()
        participant.drawCard(Card(ACE, SPADE))

        originSize + 1 shouldBe participant.getHandSize()
    }

    "카드를 두 장 뽑으면, 카드 패의 총 개수는 2 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.getHandSize()
        participant.drawCards(listOf(Card(ACE, SPADE), Card(ACE, HEART)))

        originSize + 2 shouldBe participant.getHandSize()
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘으면 bust 여부를 true로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(
            listOf(
                Card(KING, DIAMOND),
                Card(KING, SPADE),
                Card(KING, HEART)
            )
        )

        participant.isBust() shouldBe true
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘지 않으면 bust 여부를 false로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(listOf(Card(KING, DIAMOND)))

        participant.isBust() shouldBe false
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘으면 bust가 아닌지의 여부를 false로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(
            listOf(
                Card(KING, DIAMOND),
                Card(KING, SPADE),
                Card(KING, HEART)
            )
        )

        participant.isNotBust() shouldBe false
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘지 않으면 bust가 아닌지의 여부를 true 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(listOf(Card(KING, DIAMOND)))

        participant.isNotBust() shouldBe true
    }

    "갖고 있는 카드 패의 총 점수는 각 카드의 점수의 총합을 반환한다." {
        forAll(
            row(listOf(Card(TWO, SPADE), Card(EIGHT, SPADE)), 10),
            row(listOf(Card(THREE, SPADE), Card(FIVE, SPADE)), 8),
            row(listOf(Card(FOUR, SPADE), Card(SIX, SPADE)), 10),
            row(listOf(Card(KING, SPADE), Card(KING, SPADE)), 20),
            row(listOf(Card(KING, SPADE), Card(JACK, SPADE)), 20),
            row(listOf(Card(QUEEN, SPADE), Card(EIGHT, SPADE)), 18),
        ) { cards: List<Card>, expect: Int ->
            val participant = Participant("Alex")
            participant.drawCards(cards)

            participant.getScore() shouldBe expect
        }
    }
})
