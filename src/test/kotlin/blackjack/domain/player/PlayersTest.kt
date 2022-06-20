package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

private fun `starting cards`() = listOf(Card.Two(CardSuit.CLOVER), Card.Three(CardSuit.CLOVER))
class PlayersTest {
    @Test
    fun `Players 는 딜러와 여러명의 플레이어로 구성되어 있다`() {
        val dealer = Dealer(`starting cards`())
        val players = listOf(Player("a", `starting cards`()), Player("b", `starting cards`()))
        val allPlayers = Players(dealer, players)

        assertThat(allPlayers.dealer).isEqualTo(dealer)
        assertThat(allPlayers.list).isEqualTo(players)
    }

    @Test
    fun `총 플레이어 수가 0 명일 경우 IllegalArgumentException 이 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy { Players(Dealer(`starting cards`()), emptyList()) }
    }
}
