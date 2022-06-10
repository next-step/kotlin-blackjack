package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `발급받은 카드는 개인이 보유하고 있어야 한다`() {
        val card = Card(Card.CardSuit.SPADE, Card.CardDenomination.QUEEN)
        val player = Player("keira")
        val cards = listOf(card)

        player.offer(cards)

        val found = player.cards.first { it == card }
        assertThat(found).isNotNull
    }

    @Test
    fun `가지고 있는 카드가 중복이면 익셉션이 발생한다`() {
        val card = Card(Card.CardSuit.SPADE, Card.CardDenomination.QUEEN)
        val player = Player("keira")
        val cards = listOf(card)

        assertThrows<IllegalArgumentException> {
            player.offer(cards)
            player.offer(cards)
        }
    }

    @Test
    fun `카드의 합계는 카드의 point 의 sum 값이다`() {
        val spade = Card(Card.CardSuit.SPADE, Card.CardDenomination.EIGHT)
        val heart = Card(Card.CardSuit.HEART, Card.CardDenomination.QUEEN)
        val diamond = Card(Card.CardSuit.DIAMOND, Card.CardDenomination.ACE)
        val player = Player("keira")

        val cards = listOf(spade, heart, diamond)

        player.offer(cards)

        assertThat(player.getSumOfCards()).isEqualTo(19)
    }
}
