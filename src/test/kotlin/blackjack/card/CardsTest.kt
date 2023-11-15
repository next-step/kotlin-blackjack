package blackjack.card

import blackjack.entity.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "A카드를 포함하고 있는지 확인" {
        val cards = Cards(listOf(Card(CardNumber.A, CardShape.HEART)))
        cards.cardsContainACard shouldBe true
    }

    "A카드를 포함하고 있지 않은지 확인" {
        val cards = Cards(listOf(Card(CardNumber.THREE, CardShape.HEART)))
        cards.cardsContainACard shouldBe false
    }

    "숫자들의 합이 잘 맞는지" {
        val cardList = listOf(Card(CardNumber.THREE, CardShape.HEART), Card(CardNumber.EIGHT, CardShape.SPADE))
        val cards = Cards(cardList)
        cards.sumOfCards shouldBe 11
    }
})
