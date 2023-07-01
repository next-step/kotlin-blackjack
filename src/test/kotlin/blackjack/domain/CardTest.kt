package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({

    test("카드는 (1-9, K, Q, J, A) 중 하나의 rank 와 (하트, 스페이드, 클로바) 중 하나의 무늬를 가진다.") {
        // given
        val expectedRank = Rank.THREE
        val expectedSuit = Suit.HEART
        val card = Card(expectedRank, expectedSuit)

        // when, then
        card.rank shouldBe expectedRank
        card.suit shouldBe expectedSuit
    }

    test("카드의 점수 계산은 카드 숫자를 기본으로 한다.") {
        // given
        val card = Card(Rank.TWO)

        // when
        val score = card.getScore()

        // then
        score shouldBe 2
    }

    test("Ace 의 점수는 기본 1점이다.") {
        // given
        val card = Card(Rank.ACE)

        // when
        val score = card.getScore()

        // then
        score shouldBe 1
    }

    test("King, Queen, Jack은 각각 10으로 계산한다.") {
        // given
        listOf(Card(Rank.KING), Card(Rank.JACK), Card(Rank.QUEEN))
            .forAll {
                // then, when
                it.getScore() shouldBe 10
            }
    }
})
