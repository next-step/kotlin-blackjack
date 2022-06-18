package blackjack.domain.user

import blackjack.domain.card.Ace
import blackjack.domain.card.CardType
import blackjack.domain.card.Five
import blackjack.domain.card.Four
import blackjack.domain.card.Jack
import blackjack.domain.card.Six
import blackjack.domain.card.Two
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.18..
 */

class DealerTest {
    @Test
    fun `딜러의 카드가 16이하라면 isOverHitScore가 false다`() {
        val dealer = Dealer(Five(CardType.DIAMOND)).apply {
            hit(Ace(CardType.CLUB))
        }
        assertThat(dealer.isOverHitScore()).isFalse
    }

    @Test
    fun `딜러가 15 유저가 17인 경우 딜러 승리수는 0이다`() {
        val dealer = Dealer(Four(CardType.DIAMOND)).apply {
            hit(Ace(CardType.CLUB))
        }
        val user = User("hello", listOf(Ace(CardType.HEART), Six(CardType.DIAMOND)))
        assertThat(dealer.getWinAndLose(listOf(user)).first).isEqualTo(0)
    }

    @Test
    fun `딜러가 21을 초과하면 남은 플레이어는 패에 상관없이 승리한다`() {
        val dealer = Dealer(Two(CardType.CLUB)).apply {
            hit(Jack(CardType.HEART))
            hit(Jack(CardType.HEART))
        }
        val user1 = User("hello", listOf(Ace(CardType.HEART), Six(CardType.DIAMOND)))
        val user2 = User("ggg", listOf(Two(CardType.CLUB), Jack(CardType.HEART)))
        assertThat(dealer.getWinAndLose(listOf(user1, user2)).first).isEqualTo(0)
    }
}
