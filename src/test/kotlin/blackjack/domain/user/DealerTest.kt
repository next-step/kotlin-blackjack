package blackjack.domain.user

import blackjack.domain.card.ACE
import blackjack.domain.card.CardType
import blackjack.domain.card.FIVE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.18..
 */

class DealerTest {
    @Test
    fun `딜러의 카드가 16이하라면 isOverHitScore가 false다`() {
        val dealer = Dealer(listOf(ACE(CardType.CLUB), FIVE(CardType.DIAMOND)))
        assertThat(dealer.isOverHitScore()).isFalse
    }
}
