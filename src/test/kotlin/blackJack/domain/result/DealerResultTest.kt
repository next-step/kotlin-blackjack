package blackJack.domain.result

import blackJack.domain.card.Card
import blackJack.domain.enums.Rank.*
import blackJack.domain.enums.Suit.*
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import blackJack.domain.player.Players
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerResultTest {

    private lateinit var player: Players
    private lateinit var players: Players
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Players(listOf(Player("pobi", 10000)))
        dealer = Dealer("dealer")
        players = Players(listOf(Player("pobi", 10000), Player("jason", 20000)))
    }

    @Test
    fun `10000 원을 건 플레이어가 이겼을 경우 딜러는 -10000 손해를 본다`() {
        player.players[0].addCard(SPADE_ACE)
        dealer.addCard(SPADE_KING)

        val playerResults = PlayersResult.calculateResult(player, dealer)
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 졌을 경우 딜러는 10000 이득을 본다`() {
        player.players[0].addCard(SPADE_TWO)
        dealer.addCard(SPADE_KING)

        val playerResults = PlayersResult.calculateResult(player, dealer)
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 졌고, 20000 원을 건 플레이어가 이겼을 경우 경우 딜러는 -10000 손해를 본다`() {
        players.players[0].addCard(SPADE_TWO)
        players.players[1].addCard(SPADE_ACE)
        dealer.addCard(SPADE_KING)

        val playerResults = PlayersResult.calculateResult(players, dealer)
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 블랙잭으로 이겼을 경우 경우 딜러는 -15000 손해를 본다`() {
        player.players[0].addCard(SPADE_ACE)
        player.players[0].addCard(SPADE_QUEEN)
        dealer.addCard(SPADE_KING)

        val playerResults = PlayersResult.calculateResult(player, dealer)
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-15000, dealerResult.dealerProfit)
    }

    companion object {
        private val SPADE_KING = Card(SPADE, KING)
        private val SPADE_QUEEN = Card(SPADE, QUEEN)
        private val SPADE_ACE = Card(SPADE, ACE)
        private val SPADE_TWO = Card(SPADE, TWO)
    }
}
