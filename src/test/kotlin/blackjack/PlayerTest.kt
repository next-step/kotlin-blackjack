package blackjack

import blackjack.domain.Player
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape
import blackjack.domain.interfaces.FixedCardFactory
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
