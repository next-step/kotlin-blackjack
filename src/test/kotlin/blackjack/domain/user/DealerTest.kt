package blackjack.domain.user

import blackjack.domain.card.ACE
import blackjack.domain.card.CardType
import blackjack.domain.card.FIVE
import blackjack.domain.card.FOUR
import blackjack.domain.card.JACK
import blackjack.domain.card.SIX
import blackjack.domain.card.TWO
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
    fun `딜러가 15 유저가 17인 경우 딜러 승리수는 0이다`() {
        val dealer = Dealer(listOf(ACE(CardType.CLUB), FOUR(CardType.DIAMOND)))
        val user = User("hello", listOf(ACE(CardType.HEART), SIX(CardType.DIAMOND)))
        assertThat(dealer.getWinAndLose(listOf(user)).first).isEqualTo(0)
    }

    @Test
    fun `딜러가 21을 초과하면 남은 플레이어는 패에 상관없이 승리한다`() {
        val dealer = Dealer(listOf(TWO(CardType.CLUB), JACK(CardType.DIAMOND), JACK(CardType.HEART)))
        val user1 = User("hello", listOf(ACE(CardType.HEART), SIX(CardType.DIAMOND)))
        val user2 = User("ggg", listOf(TWO(CardType.CLUB), JACK(CardType.DIAMOND), JACK(CardType.HEART)))
        assertThat(dealer.getWinAndLose(listOf(user1, user2)).first).isEqualTo(0)
    }
}
