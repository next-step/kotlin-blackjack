package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    context("Cards 클래스") {

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
            cards.add(newCard)

            cards.all shouldContainExactly listOf(card1, card2, newCard)
        }

        test("점수를 올바르게 계산할 수 있다.") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE) // 1 또는 11
            val card2 = Card(CardNumber.KING, CardType.HEART) // 10

            val cards = Cards.of(listOf(card1, card2))
            val score = cards.calculateScore()

            // 가능한 점수는 1+10=11 또는 11+10=21 중 최대값인 21
            score shouldBe 21
        }

        test("21을 초과하지 않는 최대 점수를 반환한다.") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE) // 1 또는 11
            val card2 = Card(CardNumber.ACE, CardType.DIAMOND) // 1 또는 11
            val card3 = Card(CardNumber.KING, CardType.HEART) // 10

            val cards = Cards.of(listOf(card1, card2, card3))
            val score = cards.calculateScore()

            // 가능한 점수는 1+1+10=12, 1+11+10=22, 11+1+10=22, 11+11+10=32 중 최대값 12
            score shouldBe 12
        }

        test("모든 점수가 21을 초과하면 최소 점수를 반환한다.") {
            val card1 = Card(CardNumber.KING, CardType.SPADE) // 10
            val card2 = Card(CardNumber.KING, CardType.HEART) // 10
            val card3 = Card(CardNumber.QUEEN, CardType.DIAMOND) // 10

            val cards = Cards.of(listOf(card1, card2, card3))
            val score = cards.calculateScore()

            // 가능한 점수는 10+10+10=30, 최소값 30 반환
            score shouldBe 30
        }
    }
})
