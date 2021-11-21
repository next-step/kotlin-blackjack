package blackjack.domain.player.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임상태(PlayingState)")
internal class PlayingStateTest {

    @Test
    fun `종료 여부를 반환한다`() {
        assertAll(
            { assertThat(Running.isFinish()).isFalse() },
            { assertThat(End.isFinish()).isTrue() }
        )
    }

    @Test
    fun `다음 카드 드로우에따라 알맞은 상태를 반환한다`() {
        assertAll(
            { assertThat(PlayingState.of(true).isFinish()).isFalse() },
            { assertThat(PlayingState.of(false).isFinish()).isTrue() },
        )
    }
}
