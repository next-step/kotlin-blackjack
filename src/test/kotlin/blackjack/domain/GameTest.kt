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
            Pair(CardScore.SEVEN, Suit.HEART)
        ).map { Card(it) }.toSet()

        normalGame = Game("first, second", Dealer(Deck(cardsForDeck)))
        gameWithEmptyDeck = Game("first, second", Dealer(Deck(emptySet())))
        firstPlayer = normalGame.currentPlayer()
    }

    @Test
    fun `set up with two cards for each player`() {
        assertThat(firstPlayer.amountOfCards()).isEqualTo(2)
    }

    @DisplayName("대답이 STAND면 카드 추가 없이 턴이 넘어가며, HIT면 카드 한 장을 받은 player가 반환된다 (디폴트 카드 개수: 2)")
    @Test
    fun `give a chance to draw to a player`() {
        // when
        val firstPlayerToStand = normalGame.giveChanceToDraw(REPLY_STAND)
        val secondPlayerToHit = normalGame.giveChanceToDraw(REPLY_HIT)

        // then
        assertThat(firstPlayerToStand?.amountOfCards()).isEqualTo(2)
        assertThat(secondPlayerToHit?.amountOfCards()).isEqualTo(3)
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
