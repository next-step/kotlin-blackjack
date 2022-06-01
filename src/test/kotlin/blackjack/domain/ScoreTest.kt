package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class ScoreTest : StringSpec({
    "점수 객체를 생성할 수 있다." {
        shouldNotThrow<Throwable> { Score(1) }
    }

    "음수 점수는 생성할수 없다." {
        shouldThrow<IllegalArgumentException> { Score(-1) }
    }
})
