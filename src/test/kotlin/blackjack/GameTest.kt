package blackjack

import domain.dealer.Dealer
import domain.player.Players
import domain.turn.Game
import domain.turn.InitialTurn
import domain.turn.IntermediateTurn
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
        val game = Game(InitialTurn, Dealer(), players)
        game.proceed()
        game.players.list.forEach {
            assertThat(it.cards.current()).size().isEqualTo(2)
        }
    }

    @Test
    fun `카드 더 받을 수 있는 플레이어 목록 구하기 테스트`() {
        val players = playersWithOnePlayer
        val game = Game(InitialTurn, Dealer(), players, cardDeckOnlyHaveKingQueenJack)
        game.proceed()

        assertThat(
            game.playersCanTakeNextTurn()
        ).isEqualTo(players)

        game.proceed()

        assertThat(
            game.playersCanTakeNextTurn()
        ).isEqualTo(Players(emptyList()))
    }

    @Test
    fun `카드 추가 지급 테스트`() {
        val game = Game(IntermediateTurn, Dealer(), playersWithTwoPlayer)
        game.proceed()

        game.players.list.forEach {
            assertThat(it.cards.current()).size().isEqualTo(1)
        }
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
