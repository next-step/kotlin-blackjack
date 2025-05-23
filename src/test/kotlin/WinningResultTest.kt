import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import participant.Dealer
import participant.Player
import state.Blackjack
import state.Bust
import state.Stay

class WinningResultTest {
    private lateinit var winningResult: WinningResult
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @Test
    fun `when the dealer is bust, the player is bust then player lose`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Bust(hand))
        player = Player(name = "seokjin", state = Bust(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.lose).isEqualTo(1)
    }

    @Test
    fun `when the dealer is bust, the player is not bust then player win`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Bust(hand))
        player = Player(name = "seokjin", state = Stay(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.win).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Stay, the player is bust then player lose`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Stay(hand))
        player = Player(name = "seokjin", state = Bust(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.lose).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Stay, the player is stay and dealer's score is high then player lose`() {
        // given
        val dealerHand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val playerHand = Hand(listOf(CLUB_THREE, CLUB_THREE))
        dealer = Dealer(state = Stay(dealerHand))
        player = Player(name = "seokjin", state = Stay(playerHand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.lose).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Stay, the player is stay and dealer's score is low then player lose`() {
        // given
        val dealerHand = Hand(listOf(CLUB_THREE, CLUB_THREE))
        val playerHand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        dealer = Dealer(state = Stay(dealerHand))
        player = Player(name = "seokjin", state = Stay(playerHand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.win).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Stay, the player is stay and scores are same then draw`() {
        // given
        val dealerHand = Hand(listOf(CLUB_THREE, CLUB_THREE))
        val playerHand = Hand(listOf(CLUB_THREE, CLUB_THREE))
        dealer = Dealer(state = Stay(dealerHand))
        player = Player(name = "seokjin", state = Stay(playerHand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.draw).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Blackjack, the player is Bust then player lose`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Blackjack(hand))
        player = Player(name = "seokjin", state = Bust(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.lose).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Blackjack, the player is Blackjack then draw`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Blackjack(hand))
        player = Player(name = "seokjin", state = Blackjack(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.draw).isEqualTo(1)
    }

    @Test
    fun `when the dealer is Blackjack, the player is Stay then player lose`() {
        // given
        val hand = Hand(emptyList())
        dealer = Dealer(state = Blackjack(hand))
        player = Player(name = "seokjin", state = Stay(hand))
        winningResult = WinningResult(dealer)

        // when
        val result = winningResult.versus(player)

        // then
        assertThat(result.lose).isEqualTo(1)
    }
}
