package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {

    @ParameterizedTest
    @ValueSource(ints = [2, 5, 8])
    fun `블랙잭 참가 플에이어는 최소 2명 이상 최대 8명 이하이다`(createPlayCount: Int) {
        val participants: List<GamePlayer> = mutableListOf<GamePlayer>().apply {
            repeat(createPlayCount) { count ->
                add(GamePlayer("$count"))
            }
        }
        val players = Players(participants)
        assertThat(players).isNotNull
    }

    @Test
    fun `블랙잭 참가 플에이어가 2명 미만시 에러가 발생한다`() {
        val participants: List<GamePlayer> = mutableListOf<GamePlayer>().apply {
            add(GamePlayer("고니"))
        }
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Players(participants) }
    }

    @Test
    fun `블랙잭 참가 플에이어가 8명 초과시 에러가 발생한다`() {
        val participants: List<GamePlayer> = mutableListOf<GamePlayer>().apply {
            repeat(9) { count ->
                add(GamePlayer("$count"))
            }
        }
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            val players = Players(participants)
        }
    }
}
