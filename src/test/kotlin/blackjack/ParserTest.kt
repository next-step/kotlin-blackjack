package blackjack

import blackjack.service.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {

    private val parser = Parser()

    @Test
    fun `Input 을 parse 했을 때 , 을 기준으로 분리`() {
        val input = "pobi,jason"
        val actual = parser.parse(input)

        assertEquals(2, actual.size)
        assertEquals("pobi", actual[0])
        assertEquals("jason", actual[1])
    }
}
