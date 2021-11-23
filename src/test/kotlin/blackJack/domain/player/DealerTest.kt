package blackJack.domain.player

import blackJack.domain.card.Card
import blackJack.domain.card.Denomination
import blackJack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러의 이름은 딜러이다`() {
        // given
        val dealer = Dealer()

        // when
        val dealerName = dealer.name

        // then
        assertThat(dealerName).isEqualTo(Dealer.DEALER_NAME)
    }

    @Test
    fun `딜러는 17이상이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard() {
            Card(Suit.HEARTS, Denomination.SEVEN)
        }
        dealer.receiveCard() {
            Card(Suit.SPADES, Denomination.KING)
        }

        // when
        val ableReceivedCard = dealer.getAbleReceivedCard()

        // then
        assertThat(ableReceivedCard).isFalse
    }

    @Test
    fun `딜러는 17이하이면 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard() {
            Card(Suit.HEARTS, Denomination.TWO)
        }
        dealer.receiveCard() {
            Card(Suit.SPADES, Denomination.KING)
        }

        // when
        val ableReceivedCard = dealer.getAbleReceivedCard()

        // then
        assertThat(ableReceivedCard).isTrue
    }
}
