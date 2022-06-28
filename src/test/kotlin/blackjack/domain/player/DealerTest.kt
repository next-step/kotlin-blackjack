package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.state.Ready
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `딜러의 첫 게임 상태는 Ready 이다`() {
        val dealer = Dealer()

        assertAll("프로퍼티 값", {
            assertThat(dealer.name).isEqualTo("딜러")
            assertThat(dealer.gameStatus.state).isInstanceOf(Ready::class.java)
        })
    }

    @Test
    fun `딜러 확인`() {
        val dealer = Dealer()
        assertThat(dealer.isDealer()).isTrue
    }

    @Test
    fun `딜러가 카드 Card Draw 시 동일한 카드인지 확인한다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardShape.CLOVER, CardSymbol.ACE))

        assertThat(dealer.cards[0]).isEqualTo(Card(CardShape.CLOVER, CardSymbol.ACE))
    }
}
