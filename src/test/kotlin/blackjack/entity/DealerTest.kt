package blackjack.entity

import blackjack.BlackJack
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DealerTest {
    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다`() {
        // given
        val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.THREE))
        val wallet = Wallet(cards)
        val dealer = Dealer(wallet)

        // when
        val walletCards = dealer.checkDrawingCondition(dealer).getWalletCards()

        // then
        Assertions.assertThat(walletCards.size).isEqualTo(cards.size+1)
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 17점 이상이면 추가로 받을 수 없다`() {
        // given
        val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.TEN), Card(Shape.CLOVER, CardNumber.NINE))
        val wallet = Wallet(cards)
        val dealer = Dealer(wallet)

        // when
        val walletCards = dealer.checkDrawingCondition(dealer).getWalletCards()

        // then
        Assertions.assertThat(walletCards.size).isEqualTo(cards.size)
    }

    @Test
    fun `딜러 이름 기본 값은 '딜러' 이다`() {
        // given
        val dealer = BlackJack().getDealer()
        val defaultDealerName = "딜러"

        // when
        val dealerName = dealer.name

        // then
        Assertions.assertThat(dealerName).isEqualTo(defaultDealerName)
    }

    @Test
    fun `딜러의 기본 limit 값은 17이다`() {
        // given
        val dealer = BlackJack().getDealer()
        val defaultDealerLimit = 17

        // when
        val dealerLimit = dealer.limit

        // then
        Assertions.assertThat(dealerLimit).isEqualTo(defaultDealerLimit)
    }
}