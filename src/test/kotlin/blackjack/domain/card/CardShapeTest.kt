package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

class CardShapeTest : StringSpec({
    "카드 모양은 스페이드, 다이아, 하트, 클로버로 이루어져 있다." {
        val shapes = listOf(CardShape.Diamond, CardShape.Spade, CardShape.Heart, CardShape.Clover)
        shapes shouldContainAll CardShape.entries.toList()
    }
})
