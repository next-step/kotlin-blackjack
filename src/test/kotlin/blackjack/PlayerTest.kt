package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `제공한 카드를 가지고있는지 테스트`() {
        val card = Card(CardNumber.ACE, CardShape.HEART)
        val player = Player("name")

        player.offer(card)

        assertThat(player.cards).hasSize(1)

        val found = player.cards.firstOrNull {it == card }
        assertThat(found).isNotNull
    }
}
