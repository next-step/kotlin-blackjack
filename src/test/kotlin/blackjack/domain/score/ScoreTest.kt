package blackjack.domain.score

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({

    "21을 초과하면 bust 상태가 된다" {
        Score(20).isBust shouldBe false
        Score(21).isBust shouldBe false
        Score(22).isBust shouldBe true
    }

    "21 이하면 alive 상태가 된다" {
        Score(20).isAlive shouldBe true
        Score(21).isAlive shouldBe true
        Score(22).isAlive shouldBe false
    }
})
