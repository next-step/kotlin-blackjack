package blackjack.domain

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {

    @ParameterizedTest
    @MethodSource("createCardData")
    fun `카드 생성`(pair: Pair<Char, String>) {
        assertDoesNotThrow { Card(pair.first, pair.second) }
    }

    companion object {
        @JvmStatic
        fun createCardData(): List<Arguments> {
            return listOf(Arguments.of('A' to "SPADE"), Arguments.of('2' to "CLUB"))
        }
    }

}
