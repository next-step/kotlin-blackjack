package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TrumpTest : StringSpec({
    "트럼프를 조회하면 카드의 조합 56개가 반환된다" {
        // when
        val trump = Trump(PokerNumber.pokerNumbers(), PokerShape.pokerShapes())
        // then
        trump.cards.count() shouldBe 56
    }
})
