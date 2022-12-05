package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "트럼프를 조회하면 카드의 조합 52개가 반환된다" {
        // when
        val trump = Deck()
        // then
        trump.cards.count() shouldBe 52
    }
})
