package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `게임 참가 시 2장의 카드 받기`() {
        val player = Player("moshi").apply {
            requestDeck(Deck.pop())
            requestDeck(Deck.pop())
        }

        assertThat(player.myReceivedDeck.size).isEqualTo(2)
    }
}
