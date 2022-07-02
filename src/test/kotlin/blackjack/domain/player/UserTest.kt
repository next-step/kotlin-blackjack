package blackjack.domain.player

import blackjack.domain.card.Ace
import blackjack.domain.card.CardType
import blackjack.domain.card.Jack
import blackjack.domain.card.Nine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UserTest {
    @ParameterizedTest
    @EmptySource
    fun `이름이 빈칸일 경우 IllegalArgumentException을 던진다`(source: String) {
        assertThrows<IllegalArgumentException> {
            User(source, listOf())
        }
    }

    @Test
    fun `게임 승리시 배팅금액을 받는다`() {
        val user1 = User("link", Ace(CardType.DIAMOND), Nine(CardType.SPADE)).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(listOf(Ace(CardType.SPADE)))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(10000)
    }

    @Test
    fun `게임 패배시 배팅금액을 잃는다`() {
        val user1 = User("link", Nine(CardType.SPADE)).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(listOf(Ace(CardType.SPADE)))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(-10000)
    }

    @Test
    fun `유저가 블랙잭으로 승리한경우 승리시 일점오배를 받는다`() {
        val user1 = User("link", Ace(CardType.DIAMOND), Jack(CardType.SPADE)).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(listOf(Ace(CardType.SPADE)))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(15000)
    }

    @Test
    fun `무승부시 배팅금액을 돌려 받는다`() {
        val user1 = User("link", Ace(CardType.DIAMOND)).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(listOf(Ace(CardType.SPADE)))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(0)
    }
}
