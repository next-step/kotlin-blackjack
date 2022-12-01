package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PorkerShapeTest : StringSpec({

    "포커모양 조회를 하면  4개를 반환한다" {
        // when
        val pokerShapes = PokerShape.pokerShapes()
        // then
        pokerShapes.size shouldBe 4
    }
})