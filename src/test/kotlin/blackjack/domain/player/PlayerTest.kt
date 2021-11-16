package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("참가자(Player)")
internal class PlayerTest {

    @ParameterizedTest(name = "입력값 : {0}")
    @ValueSource(strings = ["a", "b", "c", "ab", "abc"])
    fun `비거나 공백이 아닌 문자열로 사용자를 만들 수 있다`(name: String) {
        val player = Player.fromName(name)

        assertAll(
            { assertThat(player).isNotNull },
            { assertThat(player).isExactlyInstanceOf(Player::class.java) }
        )
    }

    @Test
    fun `사용자는 기본적으로 플레잉중인 상태다`() {
        val player = Player.fromName("test")

        assertThat(player.isFinished()).isFalse
    }

    @Test
    fun `더이상 게임을 진행하지 않는다면 플레잉이 끝난 상태다`() {
        val player = Player.fromName("test")
        val finishedPlayer = player.continuePlayingTheGame("n")

        assertThat(finishedPlayer.isFinished()).isTrue
    }
}
