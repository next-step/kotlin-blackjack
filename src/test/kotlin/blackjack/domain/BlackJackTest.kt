package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `게임을 시작하면 각 플레이어와 딜러에게 두 장의 카드를 지급한다`() {
        val blackJack = BlackJack(
            Player("player1"),
            Player("player2")
        )

        assertThat(blackJack.deck.cards.size).isEqualTo(46)
        assertThat(blackJack.dealer.cards.size).isEqualTo(2)
        assertThat(blackJack.players).allMatch { it.cards.size == 2 }
    }
}
