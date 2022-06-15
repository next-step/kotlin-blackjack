package blackjack.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerParserTest {
    @Test
    fun `parsing player names string by comma delimiter`() {
        val playerNamesStr = "qyu1,qyu2,qyu3,qyu4"
        val expected = listOf("qyu1", "qyu2", "qyu3", "qyu4")

        val result = PlayerParser.parse(playerNamesStr)

        assertThat(result).isEqualTo(expected)
    }
}
