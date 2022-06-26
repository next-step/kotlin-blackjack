package blackjack.view.output.converter

import blackjack.domain.PlayerName
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerNameConverterTest {
    @Test
    fun `PlayerNameConverter는 PlayerName을 출력을 위한 문자열로 변환해 반환한다`() {
        val playerName = PlayerName("joker")

        val expected = "joker 카드"
        Assertions.assertThat(PlayerNameConverter.convert(playerName)).isEqualTo(expected)
    }
}
