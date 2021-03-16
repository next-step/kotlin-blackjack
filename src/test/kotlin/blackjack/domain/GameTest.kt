package blackjack.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class GameTest {

    @Test
    fun `create game test`() {

        // GIVEN
        val playerNames = "pobi,jason"

        // WHEN
        val game = Game(playerNames) { true }

        // THEN
        assertThat(game).isNotNull

        val players = game.players
        assertThat(players.map { it.name }).containsAll(playerNames.split(Game.NAME_DELIMETER))
        assertThat(players).allMatch {
            it.cards.isEmpty()
        }
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "      "])
    fun `illegal playerNames test`(playerNames: String) {
        Assertions.assertThatThrownBy { Game(playerNames) { true } }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageMatching("playerNames is not blank.")
    }

    @Test
    fun `game create and start test`() {

        // GIVEN
        val playerNames = "pobi,jason"

        // WHEN
        val game = Game(playerNames) { true }
            .start(::println)

        // THEN
        assertThat(game).isNotNull

        val players = game.players
        assertThat(players.map { it.name }).containsAll(playerNames.split(Game.NAME_DELIMETER))
        assertThat(players).allMatch {
            it.cards.size == Game.FIRST_DRAW_COUNT
        }
    }

    @Test
    fun `game create and start and hits test`() {

        // GIVEN
        val playerNames = "pobi,jason"

        // WHEN
        val game = Game(playerNames) { true }
            .start(::println)
            .hits(::println)

        // THEN
        assertThat(game).isNotNull

        val players = game.players
        assertThat(players.map { it.name }).containsAll(playerNames.split(Game.NAME_DELIMETER))
        assertThat(players).allMatch {
            it.score > Game.WINNER_SCORE
        }
    }
}
