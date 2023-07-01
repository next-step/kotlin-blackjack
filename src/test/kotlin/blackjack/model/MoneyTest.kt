package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

@DisplayName("돈")
class MoneyTest : StringSpec({

    "BigDecimal 로 생성" {
        listOf(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN)
            .forAll {
                shouldNotThrowAny {
                    Money(it)
                }
            }
    }

    "Int 로 생성" {
        listOf(0, 1, Int.MAX_VALUE)
            .forAll {
                shouldNotThrowAny {
                    Money(it)
                }
            }
    }

    "반대 부호 생성" {
        listOf(
            Money(-1) to Money(1),
            Money(0) to Money(0),
            Money(1) to Money(-1),
        ).forAll {
            -it.first shouldBe it.second
        }
    }

    "음수 생성" {
        listOf(
            Money(-1) to Money(-1),
            Money(0) to Money(0),
            Money(1) to Money(-1),
        ).forAll {
            it.first.negative shouldBe it.second
        }
    }
})
