package blackjack.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "Cards 의 모든 카드의 점수의 합을 반환해야 한다" {
        val actual = Cards(
            listOf(
                Card(Suit.SPADE, CardRank.ACE),
                Card(Suit.CLOVER, CardRank.THREE),
                Card(Suit.DIAMOND, CardRank.FIVE),
                Card(Suit.HEART, CardRank.EIGHT),
                Card(Suit.HEART, CardRank.KING)
            )
        ).totalScore()
        actual shouldBe (1 + 3 + 5 + 8 + 10)
    }
})
