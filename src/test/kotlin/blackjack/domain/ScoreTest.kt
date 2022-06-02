package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({
    "점수 객체를 생성할 수 있다." {
        shouldNotThrow<Throwable> { Score(1) }
    }

    "음수 점수는 생성할수 없다." {
        shouldThrow<IllegalArgumentException> { Score(-1) }
    }

    "점수가 크거나 같음을 비교할수 있다." {
        val values = listOf(NINE, EIGHT)
        values.forAll {
            NINE.isGreaterThan(it) shouldBe true
        }
        NINE.isGreaterThan(TEN) shouldBe false
    }

    "점수가 작거나 같음을 비교할수 있다." {
        val values = listOf(NINE, TEN)
        values.forAll {
            NINE.isLessThan(it) shouldBe true
        }
        NINE.isLessThan(EIGHT) shouldBe false
    }

    "점수가 작음을 비교할수 있다." {
        val values = listOf(NINE, EIGHT)
        values.forAll {
            NINE.isLess(it) shouldBe false
        }
        NINE.isLessThan(TEN) shouldBe true
    }
}) {
    companion object {
        val TEN = Score(10)
        val NINE = Score(9)
        val EIGHT = Score(8)
    }
}
