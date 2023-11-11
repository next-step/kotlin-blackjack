package blackjack.domain

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException

class CardTest {

    @ParameterizedTest
    @MethodSource("createCardData")
    fun `카드 전체 생성`(pair: Pair<Char, String>) {
        assertDoesNotThrow { Card(pair.first, pair.second) }
    }

    @Test
    fun `카드가 A, J, Q, K, 2-9 까지의 문자가 아니면 IllegalArgumentException 이 발생한다`() {
        assertThrows<IllegalArgumentException> { Card('T', "SPADE") }
    }

    companion object {
        @JvmStatic
        fun createCardData(): List<Arguments> {
            val shapes = listOf("HEART", "CLUB", "DIAMOND", "SPADE")
            val characters = listOf('A', 'K', 'Q', 'J') + ('2'..'9')
            return shapes.flatMap { suit ->
                (characters).map { num ->
                    Arguments.of(num to suit)
                }
            }

        }
    }
}
