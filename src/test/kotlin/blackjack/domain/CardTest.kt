package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드 목록에서 점수를 계산한다." {
        val cardList = listOf("10클로버", "8다이아", "Q스페이드", "A하트")
        val scores = Card.calculateCardValue(cardList)
        scores shouldBe 29
    }
})
