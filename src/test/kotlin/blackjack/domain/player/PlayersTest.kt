package blackjack.domain.player

import blackjack.common.PlayerDecision
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `총 플레이어 수가 0 명일 경우 IllegalArgumentException 이 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy { Players(emptyList()) }
    }

    @Test
    fun `모든 플레이어에게는 게임을 진행할 수 있는 턴이 주어진다`() {
        val players = Players(listOf(Player("a"), Player("b")))
        val results = players.play(CardDeck(), { PlayerDecision.HIT }) {}

        assertThat(results).hasSize(2)
        results.forEach { assertThat(it.finalState).isInstanceOf(PlayerState.Done::class.java) }
    }
}

private fun Player(name: String) = Player(name, 1000.0, `starting cards`())

private fun `starting cards`() = listOf(Card.Two(CardSuit.CLOVER), Card.Three(CardSuit.CLOVER))
