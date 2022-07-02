package blackjack.domain.player

import blackjack.domain.card.Ace
import blackjack.domain.card.CardType
import blackjack.domain.card.Five
import blackjack.domain.card.Four
import blackjack.domain.card.Jack
import blackjack.domain.card.King
import blackjack.domain.card.Nine
import blackjack.domain.card.Queen
import blackjack.domain.card.Seven
import blackjack.domain.card.Six
import blackjack.domain.card.Two
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.18..
 */

class DealerTest {
    @Test
    fun `딜러가 15 유저가 17인 경우 딜러가 유저의 배팅금액만큼 잃는다`() {
        val dealer = Dealer(Four(CardType.DIAMOND), Ace(CardType.CLUB))
        val user = User("hello", Ace(CardType.HEART), Six(CardType.DIAMOND)).apply {
            setBetMoney(10000)
        }
        user.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(-10000)
    }

    @Test
    fun `딜러가 21을 초과하면 남은 플레이어의 패에 상관없이 유저의 배팅금액만큼 잃는다`() {
        val dealer = Dealer(Two(CardType.CLUB), Jack(CardType.HEART), Jack(CardType.DIAMOND))
        val user1 = User("hello", Ace(CardType.HEART), Six(CardType.DIAMOND)).apply {
            setBetMoney(10000)
        }
        val user2 = User("ggg", Two(CardType.CLUB), Jack(CardType.HEART)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)
        user2.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(-20000)
    }

    @Test
    fun `딜러와 플레이어 둘다 bust라면 무승부로 수익은 0이다`() {
        val dealer = Dealer(Two(CardType.CLUB), Jack(CardType.HEART), Jack(CardType.DIAMOND))
        val user1 = User("hello", Queen(CardType.HEART), King(CardType.HEART), Nine(CardType.SPADE)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(0)
    }

    @Test
    fun `딜러와 플레이어의 점수가 같은데 블랙잭이 아닌우 무승부로 수익은 0이다`() {
        val dealer = Dealer(Two(CardType.CLUB), Jack(CardType.HEART))
        val user1 = User("hello", Two(CardType.HEART), King(CardType.HEART)).apply {
            setBetMoney(10000)
        }
        val user2 = User("ggg", Five(CardType.CLUB), Seven(CardType.HEART)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)
        user2.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(0)
    }

    @Test
    fun `딜러가 블랙잭 플레이어가 블랙잭이 아닌 21점이면 딜러의 승리로 유저의 배팅금액만큼 받는다`() {
        val dealer = Dealer(Ace(CardType.CLUB), Jack(CardType.HEART))
        val user1 = User("hello", Two(CardType.HEART), King(CardType.HEART), Nine(CardType.SPADE)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(10000)
    }

    @Test
    fun `딜러와 플레이어 둘다 블랙잭이면 무승부로 수익은 0이다`() {
        val dealer = Dealer(Ace(CardType.CLUB), Jack(CardType.HEART))
        val user1 = User("hello", Ace(CardType.HEART), King(CardType.HEART)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(0)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리하면 딜러는 배팅의 일점오배만큼 잃는다`() {
        val dealer = Dealer(Ace(CardType.CLUB))
        val user1 = User("hello", Ace(CardType.HEART), King(CardType.HEART)).apply {
            setBetMoney(10000)
        }
        user1.matchWithDealer(dealer)

        assertThat(dealer.income.value).isEqualTo(-15000)
    }
}
