package blackjack

import blackjack.enums.CardPoint
import blackjack.enums.CardShape
import blackjack.interfaces.FixedCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player는 카드를 소지할 수 있다`() {
        val player = Player("서정국")
        player.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        val card = player.cards[0]

        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.HEART, CardPoint.KING))
    }
}
