package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly

class CardsTest : FunSpec({
    context("Cards 클래스 테스트") {
        test("초기 카드 리스트를 생성하고 확인할 수 있다.") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE)
            val card2 = Card(CardNumber.KING, CardType.HEART)
            val cards = Cards.of(listOf(card1, card2))

            cards.all shouldContainExactly listOf(card1, card2)
        }

        test("카드를 추가할 수 있다.") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE)
            val card2 = Card(CardNumber.KING, CardType.HEART)
            val newCard = Card(CardNumber.TWO, CardType.DIAMOND)

            val cards = Cards.of(listOf(card1, card2))
            val updatedCards = cards.add(newCard)

//            cards.all shouldContainExactly listOf(card1, card2, newCard)
            updatedCards.all shouldContainExactly listOf(card1, card2, newCard)
        }
    }
})
