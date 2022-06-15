package blackjack.view.input.converter

import blackjack.domain.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayersConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["zelda,link", "mipha,revali,daruk,urbosa"])
    fun `PlayerConverter는 string input을 받아서 List Player로 변환해 반환한다`(input: String) {
        val names = input.split(",")

        val players = PlayersConverter.convert(input)

        players.forEachIndexed { index, player ->
            assertThat(player.name).isEqualTo(PlayerName(names[index]))
        }
    }
}
