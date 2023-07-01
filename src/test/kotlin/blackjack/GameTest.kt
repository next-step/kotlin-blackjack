package blackjack

import domain.Game
import domain.Player
import domain.card.Card
import domain.card.CardDeck
import domain.card.Denomination
import domain.card.Spade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTest {
    @ParameterizedTest
    @MethodSource("게임 초기화 테스트 데이터")
    fun `게임 시작 시 플레이어 초기화 테스트`(players: List<Player>) {
        assertThat(Game(players).players).isEqualTo(players)
    }

    @ParameterizedTest
    @MethodSource("게임 초기화 테스트 데이터")
    fun `게임 시작 시 플레이어에게 카드 2장씩 나눠주기 테스트`(players: List<Player>) {
        with(Game(players)) {
            start()
            players.forEach {
                assertThat(it.cards()).size().isEqualTo(2)
            }
        }
    }

    @Test
    fun `카드 더 받을 수 있는 플레이어 목록 구하기 테스트`() {
        val players = listOf(
            Player("peter"),
            Player("승현"),
        )

        val game = Game(
            players,
            object : CardDeck {
                override fun pop(): Card = Spade.get(Denomination.KING)
            }
        )

        game.start()

        assertThat(
            game.playersCanReceiveMoreCard()
        ).isEqualTo(players)
    }

    companion object {
        @JvmStatic
        fun `게임 초기화 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        Player("peter")
                    )
                ),
                Arguments.of(
                    listOf(
                        Player("peter"),
                        Player("승현"),
                    )
                ),
            )
        }
    }
}
