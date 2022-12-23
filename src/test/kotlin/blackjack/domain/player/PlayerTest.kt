package blackjack.domain.player

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import blackjack.domain.player.state.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 상태 - 생성 테스트, 블랙잭`() {
        // given
        val cards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK))
        val player = PlayerFactory.create(Name("pobi"), cards)

        // when, then
        assertThat(player.state is Blackjack).isTrue
    }

    @Test
    fun `플레이어 상태 - 생성 테스트, 히트`() {
        // given
        val cards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.TEN), PlayingCard(Suit.CLUBS, Denomination.JACK))
        val player = PlayerFactory.create(Name("pobi"), cards)

        // when, then
        assertThat(player.state is Hit).isTrue
    }
}
