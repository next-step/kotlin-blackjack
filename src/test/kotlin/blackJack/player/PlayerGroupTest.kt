package blackJack.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import player.Player
import player.PlayerGroup

class PlayerGroupTest {
    @Test
    fun `플레이어 그룹을 만든다, 플레이어가 2명 이상 26명 이하의 범위를 넘어설 때, 예외를 던진다`() {
        // given : 카드팩과  범위를 넘어서는 플레이어 리스트가 생성된다.

        val singlePlayerList = listOf(Player("OYJ"))
        val overPlayerList = List(27) { i -> Player("OYJ_$i") }

        // when : 게임에 플레이어를 주입한다
        val actualSingle = runCatching { PlayerGroup(singlePlayerList) }.exceptionOrNull()
        val actualOver = runCatching { PlayerGroup(overPlayerList) }.exceptionOrNull()

        // then : 예외를 던진다.
        assertThat(actualSingle).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(actualOver).isInstanceOf(IllegalArgumentException::class.java)
    }
}
