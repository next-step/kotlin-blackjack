package blackjack.domain

import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var normalGame: Game
    private lateinit var gameWithEmptyDeck: Game
    private lateinit var cardsForDeck: Set<Card>
    private lateinit var firstPlayer: Player

    @BeforeEach
    fun `set up`() {
        cardsForDeck = setOf(
            Pair(CardScore.THREE, Suit.HEART),
            Pair(CardScore.FOUR, Suit.HEART),
            Pair(CardScore.FIVE, Suit.HEART),
            Pair(CardScore.SIX, Suit.HEART),
            Pair(CardScore.SEVEN, Suit.HEART),
            Pair(CardScore.EIGHT, Suit.HEART),
            Pair(CardScore.NINE, Suit.HEART)
        ).map { Card(it) }.toSet()

        normalGame = Game("first, second", Dealer(Deck(cardsForDeck)))
        gameWithEmptyDeck = Game("first, second", Dealer(Deck(emptySet())))
        firstPlayer = normalGame.currentPlayer()
    }

    @Test
    fun `set up with two cards for each player`() {
        assertThat(firstPlayer.amountOfCards()).isEqualTo(2)
    }

    @DisplayName("대답이 STAND면 변화없이 턴이 넘어가며, HIT면 player에게 카드 한 장을 추가한다(디폴트 카드 개수: 2)")
    @Test
    fun `give a chance to draw to a player`() {
        // when
        val playerToStand = normalGame.giveChanceToDraw(REPLY_STAND)
        val playerToHit = normalGame.giveChanceToDraw(REPLY_HIT)

        // then
        assertThat(playerToStand?.amountOfCards()).isEqualTo(2)
        assertThat(playerToHit?.amountOfCards()).isEqualTo(3)
    }

    @DisplayName("덱이 비었으면 player가 카드를 뽑을 때 null이 반환된다")
    @Test
    fun `return null when deck is empty`() {
        // when
        val nullPlayer = gameWithEmptyDeck.giveChanceToDraw(REPLY_HIT)

        // then
        assertThat(nullPlayer).isEqualTo(null)
    }

    @Test
    fun `get current player`() {
        assertTrue(normalGame.currentPlayer() == firstPlayer)
    }

    @Test
    fun `game goes over when the turn is over`() {
        // when
        normalGame.giveChanceToDraw(REPLY_STAND)
        normalGame.giveChanceToDraw(REPLY_STAND)

        // then
        assertTrue(normalGame.isOver())
    }
}
