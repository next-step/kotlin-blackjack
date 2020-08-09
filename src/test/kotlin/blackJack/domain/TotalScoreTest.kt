package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TotalScoreTest {
    @Test
    fun no_ace() {
        val testList = listOf(5, 10)
        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(15)
    }
}
