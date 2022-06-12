package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.comparables.shouldBeGreaterThanOrEqualTo
import io.kotest.matchers.comparables.shouldBeLessThanOrEqualTo

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
            NINE shouldBeGreaterThanOrEqualTo it
        }
    }

    "점수가 작거나 같음을 비교할수 있다." {
        val values = listOf(NINE, TEN)
        values.forAll {
            NINE shouldBeLessThanOrEqualTo it
        }
    }
}) {
    companion object {
        private val TEN = Score.of(10)
        private val NINE = Score.of(9)
        private val EIGHT = Score.of(8)
    }
}
