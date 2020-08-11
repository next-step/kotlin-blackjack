package blackjack.model.card

import blackjack.model.BlackJackGame
import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import blackjack.model.player.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    private lateinit var blackJackGame: BlackJackGame
    private lateinit var dealer: Dealer
    private lateinit var gamer: Gamer
    private lateinit var players: Players

    @BeforeEach
    fun beforeTest() {
        dealer = Dealer()
        gamer = Gamer("jejun")
        players = Players(mutableListOf(dealer, gamer))

        blackJackGame = BlackJackGame(players)
    }

    @DisplayName(value = "21 이하, 딜러 승리")
    @Test
    fun dealerWin() {
        dealer.cards = Cards(mutableListOf(Card(Card.Suit.CLUBS, Card.Denomination.TEN)))
        gamer.cards = Cards(mutableListOf(Card(Card.Suit.CLUBS, Card.Denomination.NINE)))

        blackJackGame.checkWinOrLose()

        Assertions.assertThat(players.getWinnerResults()).isEqualTo(listOf("딜러:1승0패", "jejun:패"))
    }

    @DisplayName(value = "21 이하, 게이머 승리")
    @Test
    fun gamerWin() {
        dealer.cards = Cards(mutableListOf(Card(Card.Suit.CLUBS, Card.Denomination.NINE)))
        gamer.cards = Cards(mutableListOf(Card(Card.Suit.CLUBS, Card.Denomination.ACE)))

        blackJackGame.checkWinOrLose()

        Assertions.assertThat(players.getWinnerResults()).isEqualTo(listOf("딜러:0승1패", "jejun:승"))
    }

    @DisplayName(value = "21 초과, 게이머 승리")
    @Test
    fun gamerWinOverBlackJackNumber() {
        val card8 = Card(Card.Suit.CLUBS, Card.Denomination.EIGHT)
        val card9 = Card(Card.Suit.CLUBS, Card.Denomination.NINE)

        dealer.cards = Cards(mutableListOf(card8, card8, card8))
        gamer.cards = Cards(mutableListOf(card8, card8, card9))

        blackJackGame.checkWinOrLose()

        Assertions.assertThat(players.getWinnerResults()).isEqualTo(listOf("딜러:0승1패", "jejun:승"))
    }
}
