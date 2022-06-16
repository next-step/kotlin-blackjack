package blackjack.view.input.converter

import blackjack.domain.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerNamesConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["zelda,link", "mipha,revali,daruk,urbosa"])
    fun `PlayerNamesConverter는 string input을 받아서 List PlayerName으로 변환해 반환한다`(input: String) {
        val names = input.split(",")

        val playerNames = PlayerNamesConverter.convert(input)

        playerNames.forEachIndexed { index, name ->
            assertThat(name).isEqualTo(PlayerName(names[index]))
        }
    }
}
