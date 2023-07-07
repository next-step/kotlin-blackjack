package blackjack

import domain.Game
import domain.Player
import domain.card.Card
import domain.card.CardDeck
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
                assertThat(it.cards.current()).size().isEqualTo(2)
            }
        }
    }

    @Test
    fun `카드 더 받을 수 있는 플레이어 목록 구하기 테스트`() {
        val players = listOf(
            Player("peter"),
        )

        val game = Game(
            players,
            object : CardDeck {

                var popCount = 0

                val kings: List<Card> = listOf(
                    spadeKing,
                    diamondKing,
                    heartKing,
                    cloverKing,
                )

                override fun pop(): Card {
                    return kings[popCount++]
                }
            }
        )

        game.start()

        assertThat(
            game.playersCanReceiveMoreCard()
        ).isEqualTo(players)

        players.forEach {
            game.hit(it)
        }

        assertThat(
            game.playersCanReceiveMoreCard()
        ).isEqualTo(emptyList<Player>())
    }

    @Test
    fun `카드 추가 지급 테스트`() {
        val players = listOf(
            Player("peter"),
            Player("승현"),
        )

        val game = Game(players)

        players.forEach {
            game.hit(it)
        }

        game.players.forEach {
            assertThat(it.cards.current()).size().isEqualTo(1)
        }
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
