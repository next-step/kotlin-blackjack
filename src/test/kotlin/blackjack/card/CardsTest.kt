package blackjack.card

import blackjack.domain.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.ParticipantCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "A카드를 포함하고 있는지 확인" {
        val cardDeque = ArrayDeque<Card>(
            listOf(
                Card(CardNumber.J, CardShape.CLOVER),
                Card(CardNumber.TWO, CardShape.HEART),
                Card(CardNumber.A, CardShape.DIAMOND),
            )
        )
        val deque = ParticipantCards(cardDeque)
        deque.cardsContainACard shouldBe true
    }

    "A카드를 포함하고 있지 않은지 확인" {
        val cardDeque = ArrayDeque<Card>(
            listOf(
                Card(CardNumber.J, CardShape.CLOVER),
                Card(CardNumber.TWO, CardShape.HEART),
                Card(CardNumber.THREE, CardShape.DIAMOND),
            )
        )
        val deque = ParticipantCards(cardDeque)
        deque.cardsContainACard shouldBe false
    }

    "숫자들의 합이 잘 맞는지" {
        val cardSumDeque = ArrayDeque<Card>(
            listOf(Card(CardNumber.THREE, CardShape.HEART), Card(CardNumber.EIGHT, CardShape.SPADE))
        )
        val sumDeque = ParticipantCards(cardSumDeque)
        sumDeque.getCurrentScore() shouldBe 11
    }
})
