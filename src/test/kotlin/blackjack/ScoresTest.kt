package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class ScoresTest : FunSpec({
    test("점수들을 인자로 받아서 총합을 구할 수 있다.") {
        // given
        val sut = Scores(listOf(Score(1), Score(2), Score(3)))

        // when
        val result = sut.totalScore()

        // then
        result shouldBe Score(6)
    }
})
