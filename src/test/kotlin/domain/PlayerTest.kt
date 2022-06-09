package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `발급받은 카드는 개인이 보유하고 있어야 한다`() {
        val card = Card(Card.CardPattern.SPADE, Card.CardValue.QUEEN)
        val player = Player("keira")
        val cards = listOf(card)

        player.offer(cards)

        val found = player.cards.first { it == card }
        assertThat(found).isNotNull
    }

    @Test
    fun `가지고 있는 카드가 중복이면 익셉션이 발생한다`() {
        val card = Card(Card.CardPattern.SPADE, Card.CardValue.QUEEN)
        val player = Player("keira")
        val cards = listOf(card)

        assertThrows<IllegalArgumentException> {
            player.offer(cards)
            player.offer(cards)
        }
    }

    @Test
    fun `카드의 합계는 카드의 point 의 sum 값이다`() {
        val spade = Card(Card.CardPattern.SPADE, Card.CardValue.EIGHT)
        val heart = Card(Card.CardPattern.HEART, Card.CardValue.QUEEN)
        val diamond = Card(Card.CardPattern.DIAMOND, Card.CardValue.ACE)
        val player = Player("keira")

        val cards = listOf(spade, heart, diamond)

        player.offer(cards)

        assertThat(player.cardSum()).isEqualTo(19)
    }
}
