package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("사용자들(Players)")
internal class PlayersTest {

    @ParameterizedTest(name = "입력값: {0}")
    @ValueSource(strings = ["a", "a,b", "a,b,c"])
    fun `이름을 통해 참가자를 만들 수 있다`(namesString: String) {
        val players: Players = Players.of(namesString) { it.split(",") }

        assertAll(
            { assertThat(players).isNotNull },
            { assertThat(players).isExactlyInstanceOf(Players::class.java) },
        )
    }
}
