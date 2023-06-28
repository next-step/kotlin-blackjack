package blackjack.player

import domain.player.BetAmount
import domain.player.Players
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {

    @ParameterizedTest
    @MethodSource("getPlayerNames")
    fun `게임에 참여 가능한 최대 인원이 1 ~ 8명이 아니라면 IllegalArgumentException 을 발생`(players: Map<String, BetAmount>) {
        shouldThrow<IllegalArgumentException> {
            Players.createPlayers(players)
        }
    }

    companion object {
        @JvmStatic
        fun getPlayerNames(): List<Arguments> = listOf(
            Arguments.of(emptyMap<String, BetAmount>()),
            Arguments.of(
                (1..9).associate { "player$it" to BetAmount(it) },
            ),
        )
    }
}
