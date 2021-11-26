package blackjack.domain.player.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("매치 결과(MatchResult)")
internal class MatchResultTest {

    @Test
    fun `숫자에 따라 알맞는 열거형을 반환한다`() {
        assertAll(
            { assertThat(MatchResult.from(1)).isEqualTo(MatchResult.WIN) },
            { assertThat(MatchResult.from(0)).isEqualTo(MatchResult.DRAW) },
            { assertThat(MatchResult.from(-1)).isEqualTo(MatchResult.LOSE) }
        )
    }
}
