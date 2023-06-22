package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Ranks
import blackjack.domain.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드들의 합을 계산할 수 있다`() {
        val tenOfClubs = Card.createCard(Ranks.TEN, Suits.CLUBS)
        val jackOfHearts = Card.createCard(Ranks.JACK, Suits.HEARTS)
        val cards = Cards.empty()
            .add(tenOfClubs)
            .add(jackOfHearts)

        assertThat(cards.sum()).isEqualTo(20)
    }

    @Test
    fun `Ace는 1 또는 11로 계산된다`() {
        val aceOfClubs = Card.createCard(Ranks.ACE, Suits.CLUBS)
        val tenOfHearts = Card.createCard(Ranks.TEN, Suits.HEARTS)
        val cards = Cards.empty()
            .add(aceOfClubs)
            .add(tenOfHearts)

        assertThat(cards.sum()).isEqualTo(21)

        val twoOfHearts = Card.createCard(Ranks.TWO, Suits.HEARTS)
        val cards2 = Cards.empty()
            .add(aceOfClubs)
            .add(twoOfHearts)

        assertThat(cards2.sum()).isEqualTo(13)
    }
}
