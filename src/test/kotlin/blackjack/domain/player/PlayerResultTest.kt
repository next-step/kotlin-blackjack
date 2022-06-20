package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {
    @Test
    fun `플레이어 정보를 가지고 있다`() {
        val player = Player()

        assertThat(PlayerResult(player, PlayerState.Blackjack).player).isSameAs(player)
    }

    @Test
    fun `플레이어의 스코어를 저장하고 있다`() {
        val playerState = PlayerState.Blackjack
        assertThat(PlayerResult(Player(), playerState).finalState).isEqualTo(playerState)
    }
}

private fun Player() = Player("vivian", 1000.0, listOf(Card.Ace(CardSuit.CLOVER), Card.King(CardSuit.CLOVER)))
