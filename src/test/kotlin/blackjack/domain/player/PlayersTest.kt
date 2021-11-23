package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("플레이어들 (Players)")
internal class PlayersTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(strings = ["a", "a,b", "a,b,c"])
    fun `이름과 분리 전략을 사용해서 플레이어들을 생성할 수 있다`(names: String) {
        val players: Players = Players.of(names) { it.split(",") }

        assertAll(
            { assertThat(players).isNotNull },
            { assertThat(players).isExactlyInstanceOf(Players::class.java) },
        )
    }
}
