package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDequeTest : StringSpec({
    "CardNumber와 CardShape를 이용해 중복 없는 52장의 카드덱을 만든다" {
        val cardDeque = CardDeque().create()
        val expected = 52
        cardDeque.getRemainCardsCount() shouldBe expected
    }

    "CardDeque 의 한장 생성 함수가 잘 작동 한다" {
        val cardDeque = CardDeque().create()
        val expected = 51
        cardDeque.generateSingleCard()
        cardDeque.getRemainCardsCount() shouldBe expected
    }

    "CardDeque 의 두장 생성 함수가 잘 작동한다" {
        val cardDeque = CardDeque().create()
        val expected = 50
        cardDeque.generateDoubleCard()
        cardDeque.getRemainCardsCount() shouldBe expected
    }

    "CardDeque 의 특정 카드 제거가 잘 작동한다" {
        val cardDeque = CardDeque().create()
        val expected = false
        val customCard = Card(CardNumber.FOUR, CardShape.HEART)
        cardDeque.removeCustomCard(customCard)
        cardDeque.containCardRemainCards(customCard) shouldBe expected
    }

    "CardDeque 의 카드 개수 찾기가 잘 작동한다." {
        val cardDeque = CardDeque().create()
        val expected = 52
        cardDeque.getRemainCardsCount() shouldBe expected
    }
})
