package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardShapeTest : StringSpec({

    "모든 모양 목록을 반환한다" {
        val allShapes = CardShape.ALL_CARD_SHAPES
        val definedAllShapes = CardShape.values().toSet()
        val intersectSize = allShapes.intersect(definedAllShapes).size
        intersectSize shouldBe definedAllShapes.size
    }
})
