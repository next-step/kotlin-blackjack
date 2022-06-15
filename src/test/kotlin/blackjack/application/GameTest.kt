package blackjack.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.CsvSource
import util.StringArrayConverter

class GameTest {

    @ParameterizedTest
    @CsvSource(
        "qyu",
        "qyu;qyu2;qyu3;qyu4;qyu5;qyu6;qyu7;qyu8;qyu9"
    )
    fun `the game should have a number of players from 2 to 8`(
        @ConvertWith(StringArrayConverter::class) playerNames: Array<String>
    ) {
        assertThrows<IllegalArgumentException> { Game(playerNames.toList()) }
    }

    @Test
    fun `players take two cards each after game started`() {
        val playerNames = listOf("qyu1", "qyu2", "qyu3", "qyu4")

        val players = Game(playerNames).players

        assertThat(players).allMatch { it.numberOfCards() == 2 }
    }
}
