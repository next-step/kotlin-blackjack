package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RandomCardGeneratorTest : FunSpec({
    test("(1-9, K, Q, J, A) 중 하나의 rank 와 (하트, 스페이드, 클로바) 중 하나의 무늬를 가지는 카드를 랜덤으로 생성할 수 있다.") {
        // given
        val expectedRank = Rank.JACK
        val expectedSuit = Suit.HEART
        val cardGenerator = RandomCardGenerator(expectedRank, expectedSuit)

        // when
        val card = cardGenerator.generate()

        // then
        card.rank shouldBe expectedRank
        card.suit shouldBe expectedSuit
    }
})
