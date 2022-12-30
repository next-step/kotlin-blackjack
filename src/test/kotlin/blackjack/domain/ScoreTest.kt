package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ScoreTest {
    @Test
    fun `21 초과의 점수는 Burst로 취급한다`() {
        val score = Score(22)

        score.isBurst() shouldBe true
    }

    @Test
    fun `21이하의 점수는 Burst가 아니다`() {
        val score = Score(21)

        score.isBurst() shouldBe false
    }
}
