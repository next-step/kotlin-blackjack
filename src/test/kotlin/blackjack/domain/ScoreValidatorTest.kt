package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ScoreValidatorTest : FunSpec({

    test("에이스를 11로 변경할 수 있는 경우 true 를 반환한다.") {
        // given
        val score = 11
        val expected = true

        // when
        val actual = ScoreValidator.isValidBlackjackScoreWithAceAsEleven(score)

        // then
        actual shouldBe expected
    }
})
