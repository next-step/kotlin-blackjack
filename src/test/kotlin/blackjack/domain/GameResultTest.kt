package blackjack.domain

import blackjack.domain.player.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GameResultTest {
    @DisplayName("플레이어들의 승패로 딜러의 승패 반환")
    @Test
    fun dealer() {
        val result = GameResult(listOf(createPlayer("pobi"), createPlayer("jason")), Dealer())

        assertAll(
            { assertThat(result.dealer[ResultType.WIN]).isEqualTo(0) },
            { assertThat(result.dealer[ResultType.DRAW]).isEqualTo(2) },
            { assertThat(result.dealer[ResultType.LOSE]).isEqualTo(0) }
        )
    }
}
