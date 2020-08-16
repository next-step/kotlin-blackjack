package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    private val player = Player("조준희")

    @Test
    fun `이름을 가지고 있다`() {
        assertThat(player.name).isEqualTo("조준희")
    }

    @Test
    fun `카드를 받을 수 있다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)
        player.addCard(card1)
        player.addCard(card2)

        assertThat(player.cards.size).isEqualTo(2)
        assertThat(player.cards.first()).isEqualTo(card1)
        assertThat(player.cards.last()).isEqualTo(card2)
    }

    @Test
    fun `본인이 가지고 있는 카드의 합을 계산할 수 있다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)
        player.addCard(card2)
        player.addCard(card1)

        val result = player.total

        assertThat(result).isEqualTo(21)
    }

    @Test
    fun `bust 상태가 아니라면 Hit할 수 있다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)
        player.addCard(card2)
        player.addCard(card1)
        player.total

        val hit = player.hit()
        assertThat(hit).isTrue()
    }

    @Test
    fun `bust 상태라면 Hit할 수 없다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)
        val card3 = Card(Suits.DIAMONDS, Denomination.FIVE)
        val card4 = Card(Suits.DIAMONDS, Denomination.JACK)
        player.addCards(listOf(card1, card2, card3, card4))

        val total = player.total
        assertThat(total).isEqualTo(26)

        assertThat(player.hit()).isFalse()
    }
}
