package blackjack.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsParserUtilTest {

    @Test
    fun `Input 을 parse 했을 때 , 을 기준으로 분리`() {
        val input = "pobi,jason"
        val actual = PlayersParserUtil.parse(input)

        assertEquals(2, actual.size)
        assertEquals("pobi", actual[0])
        assertEquals("jason", actual[1])
    }
}
