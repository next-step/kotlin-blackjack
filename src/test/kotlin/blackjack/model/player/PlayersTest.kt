package blackjack.model.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("플레이어 컬렉션 테스트")
class PlayersTest {

    @Test
    fun `플레이어가 1명 미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { Players(listOf()) }
        assertThat(exception.message).isEqualTo("플레이어는 1명 이상이어야 합니다.")
    }

    @Test
    fun `다음 플레이어 찾는 기능이 정상 동작`() {
        // given
        val player1 = Player.from("aiden1")
        val player2 = Player.from("aiden2")
        val player3 = Player.from("aiden3")

        val players = Players(listOf(player1, player2, player3))

        // when, then
        assertThat(players.findNext(player1)).isEqualTo(player2)
        assertThat(players.findNext(player2)).isEqualTo(player3)
        assertThat(players.findNext(player3)).isEqualTo(player1)
    }
}
