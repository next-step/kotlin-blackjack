package game.blackjack.v2.domain

import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber
import game.blackjack.v2.domain.card.CardShape
import game.blackjack.v2.domain.participant.Participant
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({
    "카드를 한 장 뽑으면, 카드 패의 총 개수는 1 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.getHandCards().size
        participant.drawCard(Card(CardNumber.ACE, CardShape.SPADE))

        originSize + 1 shouldBe participant.getHandCards().size
    }

    "카드를 두 장 뽑으면, 카드 패의 총 개수는 2 증가한다." {
        val participant = Participant("Alex")
        val originSize = participant.getHandCards().size
        participant.drawCards(listOf(Card(CardNumber.ACE, CardShape.SPADE), Card(CardNumber.ACE, CardShape.HEART)))

        originSize + 2 shouldBe participant.getHandCards().size
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘으면 bust 여부를 true로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(
            listOf(
                Card(CardNumber.KING, CardShape.DIAMOND),
                Card(CardNumber.KING, CardShape.SPADE),
                Card(CardNumber.KING, CardShape.HEART)
            )
        )

        participant.isBust() shouldBe true
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘지 않으면 bust 여부를 false로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(listOf(Card(CardNumber.KING, CardShape.DIAMOND)))

        participant.isBust() shouldBe false
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘으면 bust가 아닌지의 여부를 false로 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(
            listOf(
                Card(CardNumber.KING, CardShape.DIAMOND),
                Card(CardNumber.KING, CardShape.SPADE),
                Card(CardNumber.KING, CardShape.HEART)
            )
        )

        participant.isNotBust() shouldBe false
    }

    "갖고 있는 패의 총 카드 숫자가 21을 넘지 않으면 bust가 아닌지의 여부를 true 반환한다." {
        val participant = Participant("Alex")
        participant.drawCards(listOf(Card(CardNumber.KING, CardShape.DIAMOND)))

        participant.isNotBust() shouldBe true
    }

    "갖고 있는 카드 패의 총 점수는 각 카드의 점수의 총합을 반환한다." {
        forAll(
            row(listOf(Card(CardNumber.TWO, CardShape.SPADE), Card(CardNumber.EIGHT, CardShape.SPADE)), 10),
            row(listOf(Card(CardNumber.THREE, CardShape.SPADE), Card(CardNumber.FIVE, CardShape.SPADE)), 8),
            row(listOf(Card(CardNumber.FOUR, CardShape.SPADE), Card(CardNumber.SIX, CardShape.SPADE)), 10),
            row(listOf(Card(CardNumber.KING, CardShape.SPADE), Card(CardNumber.KING, CardShape.SPADE)), 20),
            row(listOf(Card(CardNumber.KING, CardShape.SPADE), Card(CardNumber.JACK, CardShape.SPADE)), 20),
            row(listOf(Card(CardNumber.QUEEN, CardShape.SPADE), Card(CardNumber.EIGHT, CardShape.SPADE)), 18),
        ) { cards: List<Card>, expect: Int ->
            val participant = Participant("Alex")
            participant.drawCards(cards)

            participant.getScore() shouldBe expect
        }
    }
})
