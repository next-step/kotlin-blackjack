package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `발급받은 카드는 개인이 보유하고 있어야 한다`() {
        val card = Card(Card.CardPattern.SPADE, Card.CardValue.QUEEN)
        val player = Player("keira")

        player.offer(card)

        val found = player.cards.first { it == card }
        assertThat(found).isNotNull
    }

    @Test
    fun `가지고 있는 카드가 중복이면 익셉션이 발생한다`() {
        val card = Card(Card.CardPattern.SPADE, Card.CardValue.QUEEN)
        val player = Player("keira")

        assertThrows<IllegalArgumentException> {
            player.offer(card)
            player.offer(card)
        }
    }

    @Test
    fun `카드의 합계는 21을 넘을 수 없다`() {
        val spade = Card(Card.CardPattern.SPADE, Card.CardValue.EIGHT)
        val heart = Card(Card.CardPattern.HEART, Card.CardValue.QUEEN)
        val diamond = Card(Card.CardPattern.DIAMOND, Card.CardValue.ACE)
        val player = Player("keira")

        player.offer(spade)
        player.offer(heart)
        player.offer(diamond)

        assertThat(player.cardSum()).isEqualTo(19)
    }
}