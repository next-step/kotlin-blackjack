package blackjack.domain.rule

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `Score 객체는 덧셈 연산이 가능하다`() {
        // given
        val a = Score().win()
        val b = Score().win(2).draw(1)
        // when
        val result = a + b
        // then
        result.winNum shouldBe 3
        result.drawNum shouldBe 1
    }

    @Test
    fun `Score 객체 동등성 연산이 가능하다`() {
        // given
        val a = Score().win()
        val b = Score().win()
        // when
        val result = a == b
        // then
        result shouldBe true
    }

    @Test
    fun `Score 객체의 반대 결과를 리턴한다`() {
        // given
        val a = Score().win()
        // when
        val result = a.reverse()
        // then
        result shouldBe Score().lose()
    }
}
