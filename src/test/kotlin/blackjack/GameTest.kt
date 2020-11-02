package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Suit
import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue

import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game
    private lateinit var gameWithEmptyDeck: Game
    private lateinit var cardsForDeck: Set<Card>
    private lateinit var player: Player

    @BeforeEach
    fun `set up`() {
        cardsForDeck = setOf(
            Card(Pair(Suit.CLUB, Denomination.QUEEN)),
            Card(Pair(Suit.SPADE, Denomination.JACK))
        ).toSet()

        game = Game("joseph, jacob")
        gameWithEmptyDeck = Game("joseph, jacob", Deck(setOf()))
        player = game.currentPlayer()
    }

    @Test
    fun `set up with two cards for each player`() {

        assertThat(player.amountOfCards()).isEqualTo(2)
    }

    @Test
    fun `플레이어에게 드로우 할 찬스`() {

        val playerToStand = game.giveChanceToDraw(REPLY_STAND)
        val playerToHit = game.giveChanceToDraw(REPLY_HIT)

        assertThat(playerToStand?.amountOfCards()).isEqualTo(2)
        assertThat(playerToHit?.amountOfCards()).isEqualTo(3)
    }

    @Test
    fun `덱이 없으면 null`() {
        val nullPlayer = gameWithEmptyDeck.giveChanceToDraw(REPLY_HIT)
        assertThat(nullPlayer).isEqualTo(null)
    }

    @Test
    fun `game over`() {

        game.giveChanceToDraw(REPLY_STAND)
        game.giveChanceToDraw(REPLY_STAND)

        assertTrue(game.isOver())
    }
}
