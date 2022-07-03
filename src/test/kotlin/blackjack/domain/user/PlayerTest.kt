package blackjack.domain.user

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {

    @Test
    fun `Player는 이름을 가진다`() {
        val name = "han"
        val player = Player(name)

        Assertions.assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `Player는 카드를 가진다`() {
        val name = "han"
        val player = Player(name)

        Assertions.assertThat(player.name).isEqualTo(name)
        Assertions.assertThat(player.isEmptyCards()).isTrue
    }

    @Test
    fun `Player에 카드를 1장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        Assertions.assertThat(player.name).isEqualTo(name)
        Assertions.assertThat(player.isEmptyCards()).isTrue
        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        Assertions.assertThat(player.isEmptyCards()).isFalse
    }

    @Test
    fun `Player에 카드를 2장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        Assertions.assertThat(player.name).isEqualTo(name)
        Assertions.assertThat(player.isEmptyCards()).isTrue

        player.addCards(
            listOf(
                Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.TWO)
            )
        )
        Assertions.assertThat(player.isEmptyCards()).isFalse
    }

    @Test
    fun `player는 21점 이상이면 카드를 받을 수 없다`() {
        val player = Player("player")

        player.addCards(
            listOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TEN)
            )
        )

        Assertions.assertThat(player.drawable()).isFalse
        assertThrows<IllegalStateException> { player.addCard(Card(Suit.SPADE, Denomination.ACE)) }
    }
}
