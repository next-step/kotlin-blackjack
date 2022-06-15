package blackjack.ui

import blackjack.domain.Cards
import blackjack.domain.Player
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BlackjackInputViewTest {

    @ParameterizedTest
    @CsvSource(
        "qyu1, d",
        "qyu2, lasdfsadf"
    )
    fun `you can only answer y or n`(name: String, input: String) {
        val player = Player(name, Cards.shuffled())

        System.setIn(input.byteInputStream())

        assertThrows<IllegalStateException> {
            BlackjackInputView.queryTakeCard(player)
        }
    }
}
