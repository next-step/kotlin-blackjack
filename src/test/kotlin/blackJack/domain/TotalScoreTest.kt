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

    @Test
    fun has_ace() {
        val testList = listOf(1, 10)

        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun has_ace_but_do_not_plus_10() {
        val testList = listOf(10, 3, 1)

        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(14)
    }
}
