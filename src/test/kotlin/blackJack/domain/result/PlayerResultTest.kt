package blackJack.domain.result

import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.enums.BlackjackResult
import blackJack.domain.enums.Rank
import blackJack.domain.enums.Status
import blackJack.domain.enums.Suit
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerResultTest {

    private lateinit var player: Player
    private lateinit var dealer: Dealer
    private lateinit var blackJackPlayer: Player
    private lateinit var blackJackDealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player(name = "player")
        player.cards = Cards(listOf(Card(Suit.SPADE, Rank.KING), Card(Suit.SPADE, Rank.QUEEN)))
        dealer = Dealer(name = "dealer")
        dealer.cards = Cards(listOf(Card(Suit.SPADE, Rank.KING), Card(Suit.SPADE, Rank.QUEEN)))

        blackJackPlayer = Player(name = "blackjackPlayer")
        blackJackPlayer.cards = Cards(listOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.KING)))
        blackJackPlayer.status = Status.BLACKJACK
        blackJackDealer = Dealer(name = "blackjackDealer")
        blackJackDealer.cards = Cards(listOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.KING)))
        blackJackDealer.status = Status.BLACKJACK
    }

    @Test
    fun `playerScore 가 21 이상이면 LOSE 가 반환된다`() {
        player.cards.addCard(Card(Suit.SPADE, Rank.TWO))
        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.LOSE, result)
    }

    @Test
    fun `dealerScore 가 21 이상이면 WIN 가 반환된다`() {
        dealer.cards.addCard(Card(Suit.SPADE, Rank.TWO))
        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.WIN, result)
    }

    @Test
    fun `playerScore 가 dealerScore 보다 크면 WIN 가 반환된다`() {
        player.cards.addCard(Card(Suit.SPADE, Rank.ACE))
        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.WIN, result)
    }

    @Test
    fun `dealerScore 가 playerScore 보다 크면 LOSE 가 반환된다`() {
        dealer.cards.addCard(Card(Suit.SPADE, Rank.ACE))
        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.LOSE, result)
    }

    @Test
    fun `dealerScore 와 playerScore 가 같으면 DRAW 가 반환된다`() {
        val result = PlayerResult.calculateResult(player, dealer).result
        assertEquals(BlackjackResult.DRAW, result)
    }

    @Test
    fun `player 와 dealer 가 모두 블랙잭이면 DRAW 가 반환된다`() {
        val result = PlayerResult.calculateResult(blackJackPlayer, blackJackDealer).result
        assertEquals(BlackjackResult.DRAW, result)
    }

    @Test
    fun `player 만 블랙잭이면 BLACKJACK 이 반환된다`() {
        val result = PlayerResult.calculateResult(blackJackPlayer, dealer).result
        assertEquals(BlackjackResult.BLACKJACK, result)
    }

    @Test
    fun `dealer 만 블랙잭이면 LOSE 가 반환된다`() {
        val result = PlayerResult.calculateResult(player, blackJackDealer).result
        assertEquals(BlackjackResult.LOSE, result)
    }
}
