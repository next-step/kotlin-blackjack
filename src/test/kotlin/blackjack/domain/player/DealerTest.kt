package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import blackjack.domain.MatchResult
import blackjack.domain.PlayerMatchResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `16점 이하 이면 hit`() {
        val dealer = Dealer()
        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.FIVE))
        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))

        assertThat(dealer.canHit()).isEqualTo(true)

        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO))

        assertThat(dealer.canHit()).isEqualTo(false)
    }

    @Test
    fun `승패를 계산 한다`() {
        val customer = Customer("정주영")
        customer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.FIVE))
        customer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))

        val dealer = Dealer()
        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.SEVEN))
        dealer.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))

        val result = dealer.match(listOf(customer))
        val expect = PlayerMatchResult(listOf(MatchResult.WIN), mapOf(customer to MatchResult.LOSE))

        assertThat(result).isEqualTo(expect)
    }
}
