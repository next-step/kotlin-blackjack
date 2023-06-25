package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDenominationTest : StringSpec({

    "모든 끗 수 목록을 반환한다" {
        val cardDenominations = CardDenomination.ALL_CARD_DENOMINATIONS
        val definedAllCardDenominations = CardDenomination.values().toSet()
        val intersectSize = cardDenominations.intersect(definedAllCardDenominations).size
        intersectSize shouldBe definedAllCardDenominations.size
    }
})
