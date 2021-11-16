package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@Suppress("NonAsciiCharacters")
class PlayerAnswerTest {

    @ParameterizedTest
    @CsvSource(value = ["y,YES", "Y,YES", "n,NO", "N,NO", "x,NO"])
    fun `input에 알맞은 대답을 찾는다`(input: String, expected: PlayerAnswer) {
        val result = PlayerAnswer.from(input)

        assertThat(result).isEqualTo(expected)
    }
}
