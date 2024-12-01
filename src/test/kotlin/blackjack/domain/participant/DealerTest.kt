package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러의 카드 합이 16을 초과하면 카드를 더이상 받을 수 없습니다`() {
        // given
        val cards = mutableListOf(
            Card.of(CardSuit.Heart, CardRank.Ten),
            Card.of(CardSuit.Heart, CardRank.Seven),
        )
        val dealer = Dealer(cards = Cards(cards))

        // when
        val result = dealer.canReceiveCard()

        // then
        assertThat(result).isFalse()
    }

}
