package blackjack.domain.rule

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `Score 객체는 덧셈 연산이 가능하다`() {
        // given
        val a = Score(1, 0)
        val b = Score(2, 1)
        // when
        val result = a + b
        // then
        result.win shouldBe 3
        result.lose shouldBe 1
    }
}
