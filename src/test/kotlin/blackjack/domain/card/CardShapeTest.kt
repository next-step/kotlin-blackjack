package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardShapeTest : StringSpec({
    "카드 모양은 스페이드, 다이아, 하트, 클로버로 이루어져 있다." {
        val shapes = listOf(CardShape.Diamond, CardShape.Spade, CardShape.Heart, CardShape.Clover)

        shapes.forEach {
            when (it) {
                is CardShape.Spade -> it.symbol shouldBe "스페이드"
                is CardShape.Diamond -> it.symbol shouldBe "다이아"
                is CardShape.Heart -> it.symbol shouldBe "하트"
                is CardShape.Clover -> it.symbol shouldBe "클로버"
            }
        }
    }
})
