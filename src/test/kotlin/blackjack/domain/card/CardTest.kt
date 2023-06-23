package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "총 48장의 카드 목록을 반환한다" {
        val allCards = Card.ALL_CARDS
        allCards.size shouldBe 48
    }

    "모든 모양이 담긴 카드 목록을 반환한다" {
        val shapes = Card.ALL_CARDS.map { it.shape }
        val definedAllShapes = CardShape.ALL_CARD_SHAPES.toSet()
        val intersectSize = shapes.intersect(definedAllShapes).size
        intersectSize shouldBe definedAllShapes.size
    }

    "모든 끗수가 담긴 카드 목록을 반환한다" {
        val denominations = Card.ALL_CARDS.map { it.denomination }
        val definedAllDenominations = CardDenomination.ALL_CARD_DENOMINATIONS.toSet()
        val intersectSize = denominations.intersect(definedAllDenominations).size
        intersectSize shouldBe definedAllDenominations.size
    }
})
