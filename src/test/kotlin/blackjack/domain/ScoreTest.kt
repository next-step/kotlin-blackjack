package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class ScoreTest {

    @Test
    fun `Score 는 1점 보다 커야한다 1점 보다 작을 경우 예외`() {
        assertThrows<IllegalArgumentException> { Score(0) }
    }

    @Test
    fun `Score 는 1점 보다 커야한다 1점이거나 큰 경우 생성 성공`() {
        assertDoesNotThrow { Score(1) }
    }
}
