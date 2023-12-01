package blackJack.domain.result

import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.enums.BlackjackResult
import blackJack.domain.enums.Rank
import blackJack.domain.enums.Suit
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerResultTest {

    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("player")
        dealer = Dealer("dealer")
    }

    @Test
    fun `playerScore 가 21 이상이면 LOSE 가 반환된다`() {
        val cards = Cards(listOf(SPADE_KING, SPADE_TEN, SPADE_QUEEN))
        player.cards.addAllCard(cards)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.LOSE, result)
    }

    @Test
    fun `dealerScore 가 21 이상이면 WIN 가 반환된다`() {
        val cards = Cards(listOf(SPADE_KING, SPADE_TEN, SPADE_QUEEN))
        dealer.cards.addAllCard(cards)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.WIN, result)
    }

    @Test
    fun `playerScore 가 dealerScore 보다 크면 WIN 가 반환된다`() {
        val kingCard = SPADE_KING
        val fiveCard = Card(Suit.SPADE, Rank.FIVE)
        player.cards.addCard(kingCard)
        dealer.cards.addCard(fiveCard)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.WIN, result)
    }

    @Test
    fun `dealerScore 가 playerScore 보다 크면 LOSE 가 반환된다`() {
        val kingCard = SPADE_KING
        val fiveCard = Card(Suit.SPADE, Rank.FIVE)
        player.cards.addCard(fiveCard)
        dealer.cards.addCard(kingCard)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.LOSE, result)
    }

    @Test
    fun `dealerScore 와 playerScore 가 같으면 DRAW 가 반환된다`() {
        val spadeCard = SPADE_KING
        val heartCard = HEART_KING
        player.cards.addCard(spadeCard)
        dealer.cards.addCard(heartCard)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.DRAW, result)
    }

    @Test
    fun `player 와 dealer 가 모두 블랙잭이면 DRAW 가 반환된다`() {
        player.addCard(SPADE_KING)
        player.addCard(SPADE_ACE)
        dealer.addCard(HEART_KING)
        dealer.addCard(HEART_ACE)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertTrue(player.isBlackJack())
        assertTrue(dealer.isBlackJack())
        assertEquals(BlackjackResult.DRAW, result)
    }

    @Test
    fun `player 만 블랙잭이면 BLACKJACK 이 반환된다`() {
        player.addCard(SPADE_KING)
        player.addCard(SPADE_ACE)
        dealer.addCard(HEART_KING)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertTrue(player.isBlackJack())
        assertFalse(dealer.isBlackJack())
        assertEquals(BlackjackResult.BLACKJACK, result)
    }

    @Test
    fun `dealer 만 블랙잭이면 LOSE 가 반환된다`() {
        player.addCard(SPADE_KING)
        dealer.addCard(HEART_KING)
        dealer.addCard(HEART_ACE)

        val result = PlayerResult.calculateResult(player, dealer).result
        assertFalse(player.isBlackJack())
        assertTrue(dealer.isBlackJack())
        assertEquals(BlackjackResult.LOSE, result)
    }
    
    companion object {
        private val SPADE_KING = Card(Suit.SPADE, Rank.KING)
        private val SPADE_TEN = Card(Suit.SPADE, Rank.TEN)
        private val SPADE_QUEEN = Card(Suit.SPADE, Rank.QUEEN)
        private val HEART_KING = Card(Suit.HEART, Rank.KING)
        private val SPADE_ACE = Card(Suit.SPADE, Rank.ACE)
        private val HEART_ACE = Card(Suit.HEART, Rank.ACE)
    }
}
