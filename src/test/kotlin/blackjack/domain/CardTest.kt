package blackjack.domain

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {

    @ParameterizedTest
    @MethodSource("createCardData")
    fun `카드 전체 생성`(pair: Pair<CardCharacter, CardShape>) {
        assertDoesNotThrow { Card(pair.first, pair.second) }
    }

    companion object {
        @JvmStatic
        fun createCardData(): List<Arguments> {
            val shapes = listOf(*CardShape.values())
            val characters = listOf(*CardCharacter.values())
            return shapes.flatMap { suit ->
                (characters).map { num ->
                    Arguments.of(num to suit)
                }
            }
        }
    }
}
