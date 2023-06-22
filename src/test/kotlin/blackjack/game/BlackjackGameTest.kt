package blackjack.game

import domain.card.Deck
import domain.game.BlackjackGame
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BlackjackGameTest {

    @ParameterizedTest
    @MethodSource("getPlayerNames")
    fun `게임에 참여 가능한 최대 인원이 1 ~ 8명이 아니라면 IllegalArgumentException 을 발생`(playerNames: List<String>) {
        val game = BlackjackGame(Deck())

        shouldThrow<IllegalArgumentException> {
            game.initGame(playerNames)
        }
    }

    companion object {
        @JvmStatic
        fun getPlayerNames(): List<Arguments> = listOf(
            Arguments.of(emptyList<String>()),
            Arguments.of((1..9).map { it.toString() }.toList()),
        )
    }
}
