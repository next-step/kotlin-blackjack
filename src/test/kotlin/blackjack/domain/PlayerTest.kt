package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerTest {

    @Test
    fun `플레이어는 이름과 2장의 카드를 처음에 가진다`() {
        val player = Player(
            "pobi", cardDeck(Card.diamond(Number.EIGHT), Card.heart(Number.TEN))
        )

        assertThat(player.name).isEqualTo("pobi")
        assertThat(player.openedCards).containsExactlyInAnyOrder(
            Card.diamond(Number.EIGHT), Card.heart(Number.TEN)
        )
        assertThat(player.hands).containsExactlyInAnyOrder(
            Card.diamond(Number.EIGHT), Card.heart(Number.TEN)
        )
    }

    @Test
    fun `21 미만이면 플레이어는 카드를 획득할 수 있다`() {
        val player = Player(
            "pobi", cardDeck(Card.diamond(Number.QUEEN), Card.heart(Number.JACK), Card.spade(Number.ACE))
        )

        player.obtain()

        assertThat(player.openedCards).containsExactlyInAnyOrder(
            Card.diamond(Number.QUEEN), Card.heart(Number.JACK)
        )
        assertThat(player.hands).containsExactlyInAnyOrder(
            Card.diamond(Number.QUEEN), Card.heart(Number.JACK), Card.spade(Number.ACE)
        )
    }

    @Test
    fun `21 이상이면 플레이어는 카드를 획득할 수 없다`() {
        val player = Player(
            "pobi", cardDeck(Card.diamond(Number.ACE), Card.heart(Number.JACK), Card.spade(Number.ACE))
        )

        assertThat(player.isObtainable()).isFalse
        assertThrows<IllegalArgumentException> {
            player.obtain()
        }
        assertThat(player.hands).containsExactlyInAnyOrder(
            Card.diamond(Number.ACE), Card.heart(Number.JACK)
        )
    }

    @Test
    fun `플레이어는 발급 받은 카드의 총합을 계산한다`() {
        val player = Player(
            "pobi", cardDeck(Card.diamond(Number.EIGHT), Card.heart(Number.TEN))
        )

        val actual = player.sumOfCards()

        assertThat(actual).isEqualTo(18)
    }

    private fun cardDeck(vararg cards: Card): CardDeck {
        val iterator = cards.iterator()
        return CardDeck { iterator.next() }
    }
}
