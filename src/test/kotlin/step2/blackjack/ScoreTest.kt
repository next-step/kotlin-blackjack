package step2.blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import step2.blackjack.domain.model.Score

class ScoreTest: StringSpec({
    "스코어 생성시 0이 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Score.from(0)
        }
    }

    "스코어 생성시 32가 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Score.from(32)
        }
    }

    "스코어 생성시 1~31이 들어올 경우 들어온 숫자와 같은 스코어가 생성되어야 한다." {
        (1..31).forEach { score ->
            Score.from(score).toInt() shouldBe score
        }
    }
})
