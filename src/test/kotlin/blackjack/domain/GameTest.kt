package blackjack.domain

import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var game: Game
    private lateinit var gameWithEmptyDeck: Game
    private lateinit var cardsForDeck: Set<Card>
    private lateinit var player: Player

    @BeforeEach
    fun `set up`() {
        cardsForDeck = setOf(
            Pair(CardScore.THREE, Suit.HEART),
            Pair(CardScore.FOUR, Suit.HEART),
            Pair(CardScore.FIVE, Suit.HEART)
        ).map { Card(it) }.toSet()

        game = Game("mark, harry")
        gameWithEmptyDeck = Game("mark, harry", Deck(setOf()))
        player = game.currentPlayer()
    }

    @Test
    fun `set up with two cards for each player`() {
        // when
        // game.setUp()

        assertThat(player.amountOfCards()).isEqualTo(2)
    }

    @DisplayName("HIT이면 카드 한 장을 받고, STAND이면 카드를 받지 않은 player를 반환한다")
    @Test
    fun `give a chance to draw to a player`() {
        // when
        val playerToHit = game.giveChanceToDraw(REPLY_HIT)
        val playerToStand = game.giveChanceToDraw(REPLY_STAND)

        // then
        assertThat(playerToHit?.amountOfCards()).isEqualTo(3)
        assertThat(playerToStand?.amountOfCards()).isEqualTo(3)
    }

    @DisplayName("덱이 비어있으면 player가 카드를 뽑는다해도 null이 반환된다")
    @Test
    fun `return null when deck is empty`() {
        // when
        val nullPlayer = gameWithEmptyDeck.giveChanceToDraw(REPLY_HIT)

        // then
        assertThat(nullPlayer).isEqualTo(null)
    }

    @Test
    fun `get current player`() {
        assertTrue(game.currentPlayer() == player)
    }

    @Test
    fun `game goes over when the turn is over`() {
        // when
        game.giveChanceToDraw(REPLY_STAND)
        game.giveChanceToDraw(REPLY_STAND)

        // then
        assertTrue(game.isOver())
    }
}
