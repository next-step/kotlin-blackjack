package blackjack

import blackjack.card.Deck
import blackjack.card.SimpleCardCreator
import blackjack.dealer.Dealer
import blackjack.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class BlackJackGameTest {

    private lateinit var players: List<Player>
    private lateinit var blackJackGame: BlackJackGame

    @BeforeEach
    fun init() {
        players = listOf(Player("pang"), Player("yohan"))
        blackJackGame = BlackJackGame(players, Dealer(Deck(SimpleCardCreator.startCard())))
    }

    @Test
    fun `게임에 참여한 플레이어 모두를 반환한다`() {
        val allPlayer = blackJackGame.allPlayer()
        assertThat(allPlayer.size).isEqualTo(2)
    }

    @Test
    fun `최초 게임을 시작하면 플레이어는 2장씩 카드를 받는다`() {
        players.forEach {
            assertThat(it.myCards().size).isEqualTo(0)
        }

        blackJackGame.startGame()

        players.forEach {
            assertThat(it.myCards().size).isEqualTo(2)
        }
    }

    @Test
    fun `플레이 가능한 플레이어를 반환한다`() {
        val playablePlayer = blackJackGame.getPlayablePlayer()
        assertThat(playablePlayer).isNotNull
        assertThat(playablePlayer?.name).isEqualTo("pang")
    }

    @Test
    fun `플레이 가능한 플레이어가 없으면 null을 반환한다`() {
        players.map { it.stopBetting() }
        val playablePlayer = blackJackGame.getPlayablePlayer()
        assertThat(playablePlayer).isNull()
    }

    @Test
    fun `플레이어가 카드가 필요하면, 카드를 준다`() {
        val firstPlayer = players.first()
        assertThat(firstPlayer.myCards()).isEmpty()

        blackJackGame.ask(firstPlayer, true)

        assertThat(firstPlayer.myCards()).isNotEmpty
    }

    @Test
    fun `게임에 참여하지 않은 플레이어는 카드를 줄 수 없다`() {
        val invalidPlayer = Player("invalid")
        assertThat(invalidPlayer.myCards()).isEmpty()

        assertThrows<IllegalArgumentException> { blackJackGame.ask(invalidPlayer, true) }
    }
}
