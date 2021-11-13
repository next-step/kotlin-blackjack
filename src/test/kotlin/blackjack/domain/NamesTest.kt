package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource

internal class NamesTest {
    @Test
    fun `텍스트를 입력받아 이름 목록을 생성한다`() {
        val givenText = "player1,player2,player3"

        val actual = Names.generateNames(givenText)

        assertThat(actual.names).containsExactly(
            Name("player1"),
            Name("player2"),
            Name("player3"),
        )
    }

    @NullAndEmptySource
    @ParameterizedTest
    fun `공백인 텍스트를 입력하면 예외를 던진다`(givenText: String?) {
        assertThrows<IllegalArgumentException> { Names.generateNames(givenText) }
    }
}
