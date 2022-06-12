package blackjack.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class InputParserTest {
    @Test
    fun `Player 이름을 파싱할 수 있다`() {
        val input = "pobi,jason"

        val names = InputParser.parsePlayerName(input)

        assertThat(names).hasSize(2)
        assertThat(names[0]).isEqualTo("pobi")
        assertThat(names[1]).isEqualTo("jason")
    }

    @ParameterizedTest
    @ValueSource(strings = ["y", "n"])
    fun `more card input을 파싱할 수 있다`(input: String) {
        val moreCard = InputParser.parseMoreCard(input)

        assertThat(moreCard).isNotNull
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["a", "aa"])
    fun `잘못된 input 값은 more card 파싱할 수 없다`(input: String) {
        assertThrows<IllegalArgumentException> { InputParser.parseMoreCard(input) }
    }
}
