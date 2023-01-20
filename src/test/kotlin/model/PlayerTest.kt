package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `플레이어 카드를 추가한다`() {
        val player = Player("Jake")
        assertThat(player.receiveCard(Card(CardNumber.THREE, CardShape.SPADES))).isTrue
    }

    @Test
    fun `플레이어 카드를 가져온다`() {
        val player = Player("Jake")
        player.receiveCard(Card(CardNumber.THREE, CardShape.SPADES))
        assertThat(player.openCard()[0]).isEqualTo(Card(CardNumber.THREE, CardShape.SPADES))
    }

    @Test
    fun `플레이어 이름을 가져온다`() {
        val player = Player("Jake")
        assertThat(player.notifyName()).isEqualTo("Jake")
    }
}
