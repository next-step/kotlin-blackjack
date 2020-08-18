package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TotalScoreTest {
    @Test
    fun no_ace() {
        val testList = listOf(Card(Shape.HEART, Denomination.FIVE), Card(Shape.HEART, Denomination.TEN))

        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(15)
    }

    @Test
    fun has_ace() {
        val testList = listOf(Card(Shape.HEART, Denomination.ACE), Card(Shape.HEART, Denomination.TEN))

        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun has_ace_but_do_not_plus_10() {
        val testList = listOf(
            Card(Shape.HEART, Denomination.ACE),
            Card(Shape.HEART, Denomination.TEN),
            Card(Shape.HEART, Denomination.THREE)
        )

        val totalScore = TotalScore.getScore(testList)

        assertThat(totalScore).isEqualTo(14)
    }
}
