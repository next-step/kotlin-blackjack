package blackjack

import domain.gamer.Gamers
import domain.gamer.dealer.Dealer
import domain.gamer.player.Players
import domain.turn.Game
import domain.turn.InitialTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTest {
    @ParameterizedTest
    @MethodSource("게임 초기화 테스트 데이터")
    fun `게임 시작 시 플레이어에게 카드 2장씩 나눠주기 테스트`(players: Players) {
        val game = Game(InitialTurn, Gamers.of(Dealer(), players))
        game.proceed()
        game.gamers.players.list.forEach {
            assertThat(it.cards.current()).size().isEqualTo(2)
        }
    }

    @Test
    fun `카드 더 받을 수 있는 플레이어 목록 구하기 테스트`() {
        val players = playersWithOnePlayer
        val gamer = Gamers.of(Dealer(), players)
        val game = Game(InitialTurn, gamer, cardDeckOnlyHaveKingQueenJack)
        game.proceed()

        assertThat(
            gamer.gamerToHit()
        ).isEqualTo(players.list.first())

        game.proceed()

        assertThat(
            gamer.gamerToHit()
        ).isNull()
    }

    companion object {
        @JvmStatic
        fun `게임 초기화 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    playersWithOnePlayer
                ),
                Arguments.of(
                    playersWithTwoPlayer
                ),
            )
        }
    }
}
