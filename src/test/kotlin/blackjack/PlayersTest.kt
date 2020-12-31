package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IndexOutOfBoundsException

class PlayersTest {

    private lateinit var players: Players
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var card1: Card
    private lateinit var card2: Card
    private lateinit var card3: Card
    private lateinit var card4: Card
    private lateinit var sampleDeck: Set<Card>

    @BeforeEach
    fun `set up`() {
        player1 = Player("Joseph")
        player2 = Player("Mark")
        player1.draw(Card(Pair(Denomination.KING, Suit.CLUB)))
        player1.draw(Card(Pair(Denomination.FIVE, Suit.SPADE)))
        player2.draw(Card(Pair(Denomination.FOUR, Suit.HEART)))
        player2.draw(Card(Pair(Denomination.SEVEN, Suit.DIAMOND)))
        players = Players(mutableListOf(player1, player2))
    }

    @Test
    fun `find player in list of players`() {
        assertThat(players.findPlayer(0)).isEqualTo(player1)
        assertThrows<IndexOutOfBoundsException> { (players.findPlayer(5)) }
    }

    @Test
    fun `number of members`() {
        assertThat(players.totalNumberOfPlayers()).isEqualTo(2)
    }

    @Test
    fun `amount of Cards`() {
        assertThat(players.totalAmountOfCards()).isEqualTo(4)
    }

    @Test
    fun `state of player's cards`() {
        assertThat(players.stateOfPlayerCard(0)).isEqualTo("[K클로버, 5스페이드]")
    }

}
