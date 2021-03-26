package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DealerTest {

    @Test
    fun `16점 이하 이면 hit`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.FIVE),
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN)
        )

        assertThat(dealer.canHit()).isEqualTo(true)

        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO))

        assertThat(dealer.canHit()).isEqualTo(false)
    }

    @Test
    fun `고객이 이미 burst시 수익= 고객배팅금`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.QUEEN)
        )

        val customer = Customer("고객1", 10000)
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.QUEEN))
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.SEVEN))

        assertThat(dealer.match(listOf(customer)).dealerEarning).isEqualTo(10000)
    }

    @Test
    fun `딜러가 burst시 수익= -고객베팅금`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.FIVE),
            Card.of(CardSymbol.DIAMONDS, CardNumber.SEVEN)
        )

        val customer = Customer("고객1", 10000)
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))

        assertThat(dealer.match(listOf(customer)).dealerEarning).isEqualTo(-10000)
    }

    @Test
    fun `무승부시 딜러 수익= 0원`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.QUEEN)
        )

        val customer = Customer("고객1", 10000)
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.QUEEN))

        assertThat(dealer.match(listOf(customer)).dealerEarning).isEqualTo(0)
    }

    @Test
    fun `딜러 패배시 딜러 수익= -고객배팅금`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.TWO)
        )

        val customer = Customer("고객1", 10000)
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.QUEEN))

        assertThat(dealer.match(listOf(customer)).dealerEarning).isEqualTo(-10000)
    }

    @Test
    fun `고객이 블렉잭으로 딜러 패배시 딜러 수익= -고객배팅금*2`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.TWO)
        )

        val customer = Customer("고객1", 10000)
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))
        customer.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.ACE))

        assertThat(dealer.match(listOf(customer)).dealerEarning).isEqualTo(-20000)
    }

    @Test
    fun `딜러는 16점 초과시 카드를 받을 수 없다`() {
        val dealer = createDealer(
            Card.of(CardSymbol.DIAMONDS, CardNumber.TEN),
            Card.of(CardSymbol.DIAMONDS, CardNumber.SEVEN)
        )

        assertThrows<IllegalStateException> { dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO)) }
    }

    fun createDealer(vararg cards: Card): Dealer {
        val dealer = Dealer()
        cards.forEach { dealer.acceptCard(it) }

        return dealer
    }
}
