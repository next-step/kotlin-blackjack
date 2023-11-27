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

    @Test
    fun `플레이어 그룹을 만든다, 플레이어 반환 요청할 떄, 현재 플레이 중인 플레이어를 반환한다`() {
        // given : 플레이어 그룹을 만든다.
        val playerGroup = PlayerGroup(listOf(Player("OYJ"), Player("PoPo")))

        // when : 현재 플레이어 반환을 요청한다.
        val actual = playerGroup.getCurrentPlayer()

        // then : 첫번째 플레이어가 반환된다.
        assertThat(actual).isEqualTo(playerGroup.playerList[0])
    }

    @Test
    fun `플레이어 그룹을 만든다, 플레이어 턴오버를 요청할 때, 다음 플레이어로 차례가 넘어간다`() {
        // given : 플레이어 그룹을 만든다.
        val playerGroup = PlayerGroup(listOf(Player("OYJ"), Player("PoPo")))

        // when : 플레이어 턴오버를 요청한다.
        playerGroup.turnOverPlayer()
        val actual = playerGroup.getCurrentPlayer()

        // then : 두번째 플레이어가 반환된다.
        assertThat(actual).isEqualTo(playerGroup.playerList[1])
    }
}
