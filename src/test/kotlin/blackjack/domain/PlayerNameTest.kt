package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerNameTest {
    @ParameterizedTest
    @ValueSource(strings = ["mario, luigi", "Toad"])
    fun `PlayerName은 플레이어의 이름을 보관한다`(input: String) {
        assertThat(PlayerName(input).value).isEqualTo(input)
    }
}
