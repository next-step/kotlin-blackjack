package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가지고 있다`() {
        val name = "vivian"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `플레이어는 여러장의 카드를 가질 수 있다`() {
        val player = Player("vivian")

        val firstCard = Card.Two(CardSuit.DIAMOND)
        val secondCard = Card.Ace(CardSuit.SPADE)

        assertThatNoException().isThrownBy {
            player.addCardToHand(firstCard)
            player.addCardToHand(secondCard)
        }

        assertThat(player.cards[0]).isSameAs(firstCard)
        assertThat(player.cards[1]).isSameAs(secondCard)
    }
}
