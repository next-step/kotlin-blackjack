package blackjack.domain

import blackjack.enums.CardShape
import blackjack.enums.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `카드를 받아서 현재 숫자를 계산한다(2클로버)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Two))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(CardType.Two.point)
    }
}
