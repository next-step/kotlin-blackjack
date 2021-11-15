package blackjack.domain.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임상태(PlayingState)")
internal class PlayingStateStateTest {

    @Test
    fun `종료 여부를 반환한다`() {
        assertAll(
            { assertThat(Running.isFinish()).isFalse() },
            { assertThat(End.isFinish()).isTrue() }
        )
    }
}
