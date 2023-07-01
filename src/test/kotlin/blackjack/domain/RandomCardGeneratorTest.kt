package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeTypeOf

class RandomCardGeneratorTest : FunSpec({
    test("(1-9, K, Q, J, A) 중 하나의 rank 와 (하트, 스페이드, 클로바) 중 하나의 무늬를 가지는 카드를 랜덤으로 생성할 수 있다.") {
        // given
        val cardGenerator = RandomCardGenerator()

        // when
        val actual = cardGenerator.generate()

        // then
        actual.rank.shouldBeTypeOf<Rank>()
        actual.suit.shouldBeTypeOf<Suit>()
    }
})
