package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : FunSpec({

    test("카드 리스트의 총 점수를 계산할 수 있다.") {
        // given
        val cards = Cards(Card(Rank.QUEEN, Suit.SPADE), Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))

        // when
        val actual = ScoreCalculator.calculateScore(cards)

        // then
        actual shouldBe 21
    }

    test("Ace는 21을 넘지 않으면서 가장 가깝도록 1 또는 11을 선택한다.") {
        // given
        val cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val expected = 12

        // when
        val actual = ScoreCalculator.calculateScore(cards)

        // then
        actual shouldBe expected
    }
})
