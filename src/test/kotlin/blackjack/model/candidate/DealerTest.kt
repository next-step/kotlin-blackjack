package blackjack.model.candidate

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("딜러 테스트")
class DealerTest {

    @Test
    fun `딜러가 처음에 받은 카드 2장의 합계가 16점 이하이면 1장의 카드를 추가로 받을 수 있음`() {
        // given
        val dealer = Dealer()

        // when
        dealer.receiveCard(Card(CardSymbol.하트, CardNumber.EIGHT))
        dealer.receiveCard(Card(CardSymbol.클로버, CardNumber.EIGHT))

        // then
        assertThat(dealer.needMoreCard).isTrue
    }

    @Test
    fun `딜러가 처음에 받은 카드 2장의 합계가 17점 이상이면 카드를 추가로 받을 수 없음`() {
        // given
        val dealer = Dealer()

        // when
        dealer.receiveCard(Card(CardSymbol.하트, CardNumber.EIGHT))
        dealer.receiveCard(Card(CardSymbol.클로버, CardNumber.NINE))

        // then
        assertThat(dealer.needMoreCard).isFalse
    }
}
