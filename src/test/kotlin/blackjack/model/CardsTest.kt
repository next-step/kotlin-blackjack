package blackjack.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "Cards 의 모든 카드의 점수의 합을 반환해야 한다" {
        val actual = Cards(
            listOf(
                Card(Suit.CLOVER, CardRank.THREE),
                Card(Suit.DIAMOND, CardRank.FIVE),
                Card(Suit.HEART, CardRank.EIGHT),
                Card(Suit.HEART, CardRank.KING)
            )
        ).totalScore()
        actual shouldBe (3 + 5 + 8 + 10)
    }

    "합계점수가 21를 초과 하는 경우, ACE 가 1로 인식되어야 한다" {
        val actual = Cards(
            listOf(
                Card(Suit.HEART, CardRank.ACE),
                Card(Suit.DIAMOND, CardRank.FIVE),
                Card(Suit.HEART, CardRank.EIGHT),
                Card(Suit.HEART, CardRank.KING)
            )
        ).totalScore()
        actual shouldBe (1 + 5 + 8 + 10)
    }

    "합계점수가 21를 이하인 경우, ACE 가 11로 인식되어야 한다" {
        val actual = Cards(
            listOf(
                Card(Suit.HEART, CardRank.ACE),
                Card(Suit.HEART, CardRank.THREE),
                Card(Suit.DIAMOND, CardRank.FIVE),
            )
        ).totalScore()
        actual shouldBe (11 + 3 + 5)

    }
})
