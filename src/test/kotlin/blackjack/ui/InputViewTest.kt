package blackjack.ui

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputViewTest {
    @Test
    fun `check empty input - throw exception`() {
        val inputView = InputView()
        assertThrows<IllegalArgumentException> { inputView.checkIsValidInput("") }
    }
}
