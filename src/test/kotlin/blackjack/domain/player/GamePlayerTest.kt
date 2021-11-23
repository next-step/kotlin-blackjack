package blackjack.domain.player

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 플레이어(GamePlayer)")
internal class GamePlayerTest {

    @Test
    fun `디폴트 이름과 상태로 생성가능하다`() {
        val gamePlayer: Player = GamePlayer()

        assertAll(
            { assertThat(gamePlayer).isNotNull },
            { assertThat(gamePlayer).isExactlyInstanceOf(GamePlayer::class.java) }
        )
    }
}
