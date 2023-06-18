package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class NumberCardTest : FunSpec({

    test("숫자 카드는 2 ~ 9사이의 값을 가질 수 있다") {
        (2..9).forEach {
            val actual = NumberCard(symbol = SymbolType.DIAMOND, number = it)

            actual.name() shouldBe it.toString()
            actual.score(20) shouldBe it
        }
    }

    test("숫자 카드는 2 ~ 9 외의 값으로는 만들 수 없다.") {
        listOf(0, 1, 10, 11).forEach {
            shouldThrow<IllegalArgumentException> {
                NumberCard(symbol = SymbolType.DIAMOND, number = it)
            }
        }
    }
})
