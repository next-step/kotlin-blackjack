package blackjack.domain.user

import blackjack.domain.card.Ace
import blackjack.domain.card.CardType
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
        val user1 = User("link", listOf(Ace(CardType.DIAMOND), Nine(CardType.SPADE))).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(Ace(CardType.SPADE))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(10000)
    }

    @Test
    fun `게임 패배시 배팅금액을 잃는다`() {
        val user1 = User("link", listOf(Nine(CardType.SPADE))).apply {
            setBatMoney(10000)
        }
        val dealer = Dealer(Ace(CardType.SPADE))
        assertThat(user1.getBatResult(dealer).value).isEqualTo(-10000)
    }
}
