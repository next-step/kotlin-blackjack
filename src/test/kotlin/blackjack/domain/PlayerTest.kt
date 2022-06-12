package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.TWO
import blackjack.domain.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `Player는 이름을 가진다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `Player는 카드를 가진다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)
    }

    @Test
    fun `Player에 카드를 1장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)

        player.addCard(Card(SPADE, ACE))
        assertThat(player.cards).hasSize(1)
    }

    @Test
    fun `Player에 카드를 2장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)

        player.addCards(
            listOf(
                Card(SPADE, ACE),
                Card(SPADE, TWO)
            )
        )
        assertThat(player.cards).hasSize(2)
    }
}
