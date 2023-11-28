package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가지고 있다`() {
        val player = Player(name = Name("Seongmo"))
        assertThat(player.name).isEqualTo(Name("Seongmo"))
    }

    @Test
    fun `플레이어는 카드를 가지고 있지 않을 수 있다`() {
        val player = Player(name = Name("Seongmo"))
        assertThat(player.cards).isEmpty()
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.FIVE, CardShape.CLUB)
        player.receiveCard(card)
        assertThat(player.cards.contains(card)).isTrue()
    }
}
