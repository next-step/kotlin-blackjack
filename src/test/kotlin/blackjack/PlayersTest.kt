package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IndexOutOfBoundsException

class PlayersTest {

    private lateinit var players: Players
    private lateinit var player1: Player
    private lateinit var player2: Player

    @BeforeEach
    fun `set up`() {
        player1 = Player("Joseph")
        player2 = Player("Mark")
        player1.hit(Card(Pair(Denomination.KING, Suit.CLUB)))
        player1.hit(Card(Pair(Denomination.FIVE, Suit.SPADE)))
        player2.hit(Card(Pair(Denomination.FOUR, Suit.HEART)))
        player2.hit(Card(Pair(Denomination.SEVEN, Suit.DIAMOND)))
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
    fun `state of player's cards`() {
        assertThat(players.stateOfPlayerCard()).isEqualTo("Joseph카드: [K클로버, 5스페이드]\nMark카드: [4하트, 7다이아몬드]")
    }
}
