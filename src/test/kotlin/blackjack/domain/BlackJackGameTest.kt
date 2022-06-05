package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임 카드분배 2장 테스트`() {
        val players = listOf("molly", "jayce")
        val blackJackGame = BlackJackGame.of(players)
        blackJackGame.firstCardDistribution()

        assertThat(blackJackGame.players[0].cards.size).isEqualTo(2)
    }
}
