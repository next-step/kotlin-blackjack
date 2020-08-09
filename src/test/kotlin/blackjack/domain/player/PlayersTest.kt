package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

internal class PlayersTest {

    @DisplayName("플레이어를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = ["aa, bb"])
    fun createPlayers(input: String) {
        assertThat(Players(input).list).contains(
            Player(0, "aa"),
            Player(1, "bb")
        )
    }

    @DisplayName("하나 이상의 이름이 필요하다.")
    @Test
    fun emptyPlayer() {
        assertThatThrownBy {
            Players("")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
