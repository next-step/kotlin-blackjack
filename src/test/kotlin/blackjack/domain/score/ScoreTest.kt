package blackjack.domain.score

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({
    "isBust 는 현재 점수가 블랙잭(21)보다 크면 참이다." {
        val score = Score(23)
        score.isBust() shouldBe true
    }
})
