package blackjack.model.status

import blackjack.model.card.Card
import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import blackjack.model.player.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StatusTest {
    private lateinit var dealer: Dealer
    private lateinit var gamer: Gamer
    private lateinit var players: Players

    private val cardA = Card(Card.Suit.CLUBS, Card.Denomination.ACE)
    private val card10 = Card(Card.Suit.CLUBS, Card.Denomination.TEN)
    private val card9 = Card(Card.Suit.CLUBS, Card.Denomination.NINE)

    @BeforeEach
    fun beforeTest() {
        dealer = Dealer()
        gamer = Gamer("jejun")
        players = Players(mutableListOf(dealer, gamer))
    }

    @DisplayName(value = "카드 draw 이후 PlayerStatus 확인")
    @Test
    fun dealerWin() {
        dealer.drawCard(card10)
        dealer.drawCard(cardA)
        gamer.drawCard(card10)
        gamer.drawCard(card9)

        Assertions.assertThat(dealer.status).isEqualTo(PlayerStatus.DONE)
        Assertions.assertThat(gamer.status).isEqualTo(PlayerStatus.PLAYING)
    }

    @DisplayName(value = "카드 draw 이후 GameStatus is continue")
    @Test
    fun `GameStatus is Continue`() {
        dealer.drawCard(card10)
        dealer.drawCard(cardA)
        gamer.drawCard(card10)
        gamer.drawCard(card9)

        Assertions.assertThat(players.checkGameDone()).isEqualTo(false)
    }

    @DisplayName(value = "카드 draw 이후 GameStatus is done")
    @Test
    fun `GameStatus is Done`() {
        dealer.drawCard(card10)
        dealer.drawCard(cardA)
        gamer.drawCard(card10)
        gamer.drawCard(cardA)

        Assertions.assertThat(players.checkGameDone()).isEqualTo(true)
    }
}
