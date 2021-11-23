package blackjack.domain.player

import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 참가자(GamePlayer)")
internal class GamePlayerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = GamePlayer(Name("김우재"))

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer).isNotNull },
            { AssertionsForClassTypes.assertThat(gamePlayer).isExactlyInstanceOf(GamePlayer::class.java) }
        )
    }

}

