package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드 목록에서 점수를 계산한다. - 일반 카드 조합" {
        val cardList = listOf(
            Card.of(CardNumber.TEN, CardShape.CLUB),
            Card.of(CardNumber.EIGHT, CardShape.DIAMOND),
            Card.of(CardNumber.QUEEN, CardShape.SPADE),
            Card.of(CardNumber.ACE, CardShape.HEART),
        )
        val score = Card.calculateCardValue(cardList)
        score shouldBe 29
    }

    "카드 목록에서 점수를 계산한다. - A가 11로 계산되는 경우" {
        val cardList = listOf(
            Card.of(CardNumber.FIVE, CardShape.HEART),
            Card.of(CardNumber.ACE, CardShape.CLUB),
        )
        val score = Card.calculateCardValue(cardList)
        score shouldBe 16
    }

    "카드 목록에서 점수를 계산한다. - A가 1로 계산되는 경우" {
        val cardList = listOf(
            Card.of(CardNumber.TEN, CardShape.HEART),
            Card.of(CardNumber.SIX, CardShape.SPADE),
            Card.of(CardNumber.ACE, CardShape.DIAMOND),
        )
        val score = Card.calculateCardValue(cardList)
        score shouldBe 17
    }

    "카드 목록에서 점수를 계산한다. - A가 여러 장 있는 경우" {
        val cardList = listOf(
            Card.of(CardNumber.ACE, CardShape.HEART),
            Card.of(CardNumber.ACE, CardShape.CLUB),
            Card.of(CardNumber.NINE, CardShape.SPADE),
        )
        val score = Card.calculateCardValue(cardList)
        score shouldBe 21
    }
})
