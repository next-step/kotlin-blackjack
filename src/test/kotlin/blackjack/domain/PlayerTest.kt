package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suits
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
        val card0 = Card(Suits.DIAMONDS, Denomination.ACE)
        val card1 = Card(Suits.SPADES, Denomination.TWO)
        val card2 = Card(Suits.DIAMONDS, Denomination.THREE)
        val card3 = Card(Suits.DIAMONDS, Denomination.JACK)
        val card4 = Card(Suits.DIAMONDS, Denomination.ACE)

        player.addCards(listOf(card0, card1, card2, card3, card4))

        val actual = player.total
        assertThat(actual).isEqualTo(17)
    }

    @Test
    fun `bust 상태가 아니라면 Hit할 수 있다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)

        player.addCards(listOf(card1, card2))

        val actual = player.hit()
        assertThat(actual).isTrue()
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

    @Test
    fun `stay할 수 있다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.DIAMONDS, Denomination.KING)
        val card3 = Card(Suits.DIAMONDS, Denomination.FIVE)

        player.addCards(listOf(card1, card2, card3))
        player.stay()

        assertThat(player.isStay).isTrue()
    }
}
