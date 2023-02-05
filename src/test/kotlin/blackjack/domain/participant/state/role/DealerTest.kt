package blackjack.domain.participant.state.role

import blackjack.SpadeAce
import blackjack.SpadeJack
import blackjack.SpadeTen
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 - 생성 테스트, Blackjack`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val dealer = Dealer(cards)

        // when, then
        assertThat(dealer.state is Blackjack).isTrue
    }

    @Test
    fun `딜러 - 생성 테스트, Hit`() {
        // given
        val cards = PlayingCards(SpadeTen, SpadeJack)
        val dealer = Dealer(cards)

        // when, then
        assertThat(dealer.state is Hit).isTrue
    }
}
