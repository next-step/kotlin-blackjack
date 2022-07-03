package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MatchResultTest {

    @EnumSource(MatchResult::class)
    @ParameterizedTest
    fun inverse(matchResult: MatchResult) {
        val result = when (matchResult) {
            MatchResult.WIN -> MatchResult.LOSE
            MatchResult.DRAW -> MatchResult.DRAW
            MatchResult.LOSE -> MatchResult.WIN
        }

        assertThat(matchResult.inverse()).isEqualTo(result)
    }
}
