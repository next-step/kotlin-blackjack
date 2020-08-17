package blackjack.domain

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
    private lateinit var secondPlayer: Player

    @BeforeEach
    fun `set up`() {
        cardsForDeck = setOf(
            Pair(CardScore.TWO, Suit.HEART),
            Pair(CardScore.THREE, Suit.HEART),
            Pair(CardScore.FOUR, Suit.HEART),
            Pair(CardScore.FIVE, Suit.HEART),
            Pair(CardScore.SIX, Suit.HEART),
            Pair(CardScore.SEVEN, Suit.HEART),
            Pair(CardScore.EIGHT, Suit.HEART),
            Pair(CardScore.TWO, Suit.DIAMOND)
        ).map { Card(it) }.toSet()

        normalGame = Game("first, second", Dealer(Deck(cardsForDeck)))
        gameWithEmptyDeck = Game("first, second", Dealer(Deck(emptySet())))

        firstPlayer = normalGame.players.findPlayer(0)
        secondPlayer = normalGame.players.findPlayer(1)
    }

    @DisplayName("게임 시작시 딜러와 플레이어에게 카드 2장씩 지급한다")
    @Test
    fun `set up with dealer and players`() {
        assertThat(normalGame.dealer.countOfCards()).isEqualTo(2)
        assertThat(firstPlayer.countOfCards()).isEqualTo(2)
        assertThat(secondPlayer.countOfCards()).isEqualTo(2)
    }

    @DisplayName("대답이 STAND면 그 다음 턴으로 넘어가며, HIT면 해당 player에게 카드 1장을 준다 (디폴트 카드: 2장)")
    @Test
    fun `play of players`() {
        // when
        normalGame.giveChanceToDrawing(REPLY_YES)
        normalGame.giveChanceToDrawing(REPLY_NO)
        normalGame.giveChanceToDrawing(REPLY_YES)

        // then
        assertThat(firstPlayer.countOfCards()).isEqualTo(3)
        assertThat(secondPlayer.countOfCards()).isEqualTo(3)
    }

    @DisplayName("덱이 비었으면 player가 카드를 뽑을 때 null이 반환된다")
    @Test
    fun `return null when deck is empty`() {
        // when
        val nullPlayer = gameWithEmptyDeck.giveChanceToDrawing(REPLY_YES)

        // then
        assertThat(nullPlayer).isEqualTo(null)
    }

    @DisplayName("딜러의 점수가 16이하면 카드를 한 장 더 뽑는다")
    @Test
    fun `play of dealer`() {
        // when
        val dealer = normalGame.playOfDealer()

        assertThat(dealer?.countOfCards()).isEqualTo(3)
    }

    @Test
    fun `get current player`() {
        assertTrue(normalGame.currentPlayer() == firstPlayer)
    }

    @Test
    fun `game goes over when the turn is over`() {
        // when
        normalGame.giveChanceToDrawing(REPLY_NO)
        normalGame.giveChanceToDrawing(REPLY_NO)

        // then
        assertTrue(normalGame.isOver())
    }
}
