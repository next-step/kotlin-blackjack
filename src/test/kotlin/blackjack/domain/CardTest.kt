package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드 목록에서 점수를 계산한다." {
        val cardList =
            listOf(
                Card(CardNumber.TEN, CardShape.CLUB),
                Card(CardNumber.EIGHT, CardShape.DIAMOND),
                Card(CardNumber.QUEEN, CardShape.SPADE),
                Card(CardNumber.ACE, CardShape.HEART),
            )
        val scores = Card.calculateCardValue(cardList)
        scores shouldBe 29
    }
})
