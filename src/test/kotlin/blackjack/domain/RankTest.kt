package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    fun `ACE 카드인 경우`() {
        val card = Card(Suit.CLUB, Rank.ACE)
        assertThat(card.rank.isAce(card.rank)).isEqualTo(true)
    }
}
