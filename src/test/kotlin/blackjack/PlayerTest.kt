package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `플레이어 객체를 생성한다`() {
        val player = Player("김성주")
        assertThat(player.name).isEqualTo("김성주")
    }

    @Test
    fun `플레이어 객체를 생성 시 개인 카드덱은 비어 있다`() {
        val player = Player("김성주")
        assertThat(player.cards).isEmpty()
    }

    @Test
    fun `플레이어가 카드를 받는다`() {
        val player = Player("김성주")
        player.draw(Card(CardShape.DIAMOND, CardSymbol.ACE))
        assertThat(player.cards[0]).isEqualTo(Card(CardShape.DIAMOND, CardSymbol.ACE))
    }
}
