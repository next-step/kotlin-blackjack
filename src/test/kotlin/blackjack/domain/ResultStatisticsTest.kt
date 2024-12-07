package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ResultStatisticsTest {
    @ParameterizedTest
    @CsvSource(value = ["WIN, 1, 0, 0", "LOSE, 0, 1, 0", "DRAW, 0, 0, 1"])
    fun `통계를 변경할 수 있다`(
        matchType: MatchType,
        winCount: Int,
        loseCount: Int,
        drawCount: Int,
    ) {
        val resultStatistics = ResultStatistics()

        val actual = resultStatistics.increment(matchType)

        assertThat(actual).isEqualTo(ResultStatistics(winCount, loseCount, drawCount))
    }
}
