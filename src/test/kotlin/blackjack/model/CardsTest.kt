package blackjack.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "Cards 의 모든 카드의 점수의 합을 반환해야 한다" {
        val actual = Cards(
            setOf(
                Card(Suit.SPADE, Rank.ACE),
                Card(Suit.CLOVER, Rank.THREE),
                Card(Suit.DIAMOND, Rank.FIVE),
                Card(Suit.HEART, Rank.EIGHT),
                Card(Suit.HEART, Rank.KING)
            ).toMutableSet()
        ).totalScore()
        actual shouldBe (1 + 3 + 5 + 8 + 10)
    }
})
