package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(name = "che1")

        assertThat(player.name).isEqualTo("che1")
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 5, 13])
    fun `플레이어는 카드를 받아 가지고 있을 수 있다`(cardCount: Int) {
        val player = Player(name = "che1")
        val cards = (1..cardCount).map { Card(CardSymbol.CLOVER, CardNumber(it)) }

        player.takeCards(*cards.toTypedArray())

        assertThat(player.cards.size).isEqualTo(cardCount)
    }
}
