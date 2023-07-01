package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldBeTypeOf

class RandomCardGeneratorTest : FunSpec({
    test("(1-9, K, Q, J, A) 중 하나의 rank 와 (하트, 스페이드, 클로바) 중 하나의 무늬를 가지는 카드를 랜덤으로 생성할 수 있다.") {
        // given
        val cardGenerator = RandomCardGenerator(Rank.Jack, Suit.HEART)
        val rank = Rank.Jack
        val suit = Suit.HEART

        // when
        val card = cardGenerator.generate()

        // then
        card.rank shouldBe rank
        card.suit shouldBe suit
    }
})
