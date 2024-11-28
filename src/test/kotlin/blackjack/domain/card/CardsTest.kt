package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드를 추가하면 갯수가 증가해야 한다`() {
        // given
        val cards = Cards()

        // when
        cards.add(Card(suit = CardSuit.Heart, rank = CardRank.Ace))

        // then
        assertThat(cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `ACE를 포함하고 있는 카드들 중에서 합이 21이 넘지 않으면 ACE의 값은 11이다`() {
        // given
        val cards = Cards()
        cards.add(Card(CardSuit.Heart, CardRank.Ace))
        cards.add(Card(CardSuit.Heart, CardRank.Ten))

        // when
        val result = cards.sum()

        // then
        assertThat(result).isEqualTo(21)
    }

    @Test
    fun `ACE를 포함하고 있는 카드들 중에서 합이 21이 넘으면 ACE의 값은 1이다`() {
        // given
        val cards = Cards()
        cards.add(Card(CardSuit.Heart, CardRank.Ace))
        cards.add(Card(CardSuit.Heart, CardRank.Ten))
        cards.add(Card(CardSuit.Heart, CardRank.Eight))

        // when
        val result = cards.sum()

        // then
        assertThat(result).isEqualTo(19)
    }

}
