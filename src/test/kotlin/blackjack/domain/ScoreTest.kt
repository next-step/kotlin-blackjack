package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({

    "두 점수의 합을 계산한다." {
        // given
        val score1 = Score(10)
        val score2 = Score(20)

        // when
        val result = score1 + score2

        // then
        result shouldBe Score(30)
    }

    "인자로 들어온 점수보다 현재 점수가 작으면 true, 크면 false를 반환한다." {
        forAll(
            row(Score(10), Score(20), true),
            row(Score(20), Score(10), false),
        ) { score, other, expected ->
            (score < other) shouldBe expected
        }
    }
})
