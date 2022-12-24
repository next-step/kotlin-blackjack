package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GameStateTest {

    @ParameterizedTest
    @CsvSource(value = ["WIN:1000", "LOSE:-1000", "WIN_BLACKJACK:1500"], delimiter = ':')
    fun `게임에서 결과에따라 이익금액을 얻는다`(gameState: GameState, expected: Int) {
        assertThat(gameState.benefit(Bet.of(1000))).isEqualTo(expected.toDouble())
    }
}
