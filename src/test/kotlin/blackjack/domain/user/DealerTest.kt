package blackjack.domain.user

import blackjack.domain.card.ACE
import blackjack.domain.card.CardType
import blackjack.domain.card.FIVE
import blackjack.domain.card.FOUR
import blackjack.domain.card.SIX
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

    @Test
    internal fun `딜러가 15 유저가 17인 경우 딜러 승리수는 0이다`() {
        val dealer = Dealer(listOf(ACE(CardType.CLUB), FOUR(CardType.DIAMOND)))
        val user = User("hello", listOf(ACE(CardType.HEART), SIX(CardType.DIAMOND)))
        assertThat(dealer.getWinAndLose(listOf(user)).first).isEqualTo(0)
    }
}
