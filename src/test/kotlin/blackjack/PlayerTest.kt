package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `제공한 카드를 가지고있는지 테스트`() {
        val card = Card(CardNumber.ACE, CardShape.HEART)
        val player = Player("name")

        player.offer(card)

        assertThat(player.cards).hasSize(1)

        val found = player.cards.firstOrNull { it == card }
        assertThat(found).isNotNull
    }

    @Test
    fun `플레이어의 카드의 합계를 계산하는 결과가 맞는지 테스트`() {
        val cards = listOf(
            Card(CardNumber.ACE, CardShape.CLOVER),
            Card(CardNumber.THREE, CardShape.HEART),
            Card(CardNumber.QUEEN, CardShape.CLOVER),
        )

        val player = Player("name")
        player.offer(cards)

        val answer = player.getCardSum()
        val expected = 14

        assertThat(answer).isEqualTo(expected)
    }
}
