package blackjack.domain.participant.state.role

import blackjack.SpadeAce
import blackjack.SpadeJack
import blackjack.SpadeTen
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 - 생성 테스트, 블랙잭`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val player = Player("pobi", cards)

        // when, then
        assertThat(player.state is Blackjack).isTrue
    }

    @Test
    fun `플레이어 - 생성 테스트, 히트`() {
        // given
        val cards = PlayingCards(SpadeTen, SpadeJack)
        val player = Player("pobi", cards)

        // when, then
        assertThat(player.state is Hit).isTrue
    }
}
