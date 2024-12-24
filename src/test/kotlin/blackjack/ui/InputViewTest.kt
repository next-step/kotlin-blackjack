package blackjack.ui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream

class InputViewTest {
    private val inputView = InputView()

    @Test
    fun `check empty input - throw exception`() {
        assertThrows<IllegalArgumentException> { inputView.checkIsValidInput("") }
    }

    @Test
    fun splitNameTest() {
        val mockInput = "Hong, Gil, Dong"
        System.setIn(ByteArrayInputStream(mockInput.toByteArray()))
        val players = inputView.getPlayers()

        val expected = listOf("Hong", "Gil", "Dong")
        assertEquals(expected, players)
    }

    @Test
    fun `check numberInput - throw exception`() {
        assertThrows<IllegalArgumentException> { inputView.checkValidNumber("") }
    }
}
