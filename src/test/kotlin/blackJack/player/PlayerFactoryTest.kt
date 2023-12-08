package blackJack.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import player.PlayerFactory

class PlayerFactoryTest {

    @Test
    fun `문자열을 받고, 플레이어 생성을 요청할 때, 플레이어 리스트를 반환한다`() {
        // given : ,로 구분된 문자열을 받는다.
        val playerNames = "oyj, kth, og"

        // when : 플레이어 생성을 요청한다.
        val actual = PlayerFactory.createPlayerList(playerNames).size

        // then : List<Player>를 생성한다.
        val expect = 3
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `공백이 포한된 문자열을 받고, 플레이어 생성을 요청할 때, 공백은 제외된 플레이어만 생성된다`() {
        // given : ,로 구분된 문자열을 받는다.
        val playerNames = "oyj, kth,   "

        // when : 플레이어 생성을 요청한다.
        val actual = PlayerFactory.createPlayerList(playerNames).size

        // then : List<Player>를 생성한다.
        val expect = 2
        assertThat(actual).isEqualTo(expect)
    }
}
