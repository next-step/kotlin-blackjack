package blackJack.domain

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
        assertThat(dealerName).isEqualTo("DEALER")
    }

    @Test
    fun `딜러는 플레이어가 아니다`() {
        // given
        val dealer = Dealer()

        // when
        val isPlayer = dealer.isPlayer()

        // then
        assertThat(isPlayer).isFalse
    }

    @Test
    fun `딜러는 17이상이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEARTS, Denomination.SEVEN))
        dealer.receiveCard(Card(Suit.SPADES, Denomination.KING))

        // when
        val ableReceivedCard = dealer.getAbleReceivedCard()

        // then
        assertThat(ableReceivedCard).isFalse
    }

    @Test
    fun `딜러는 17이하이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        dealer.receiveCard(Card(Suit.SPADES, Denomination.KING))

        // when
        val ableReceivedCard = dealer.getAbleReceivedCard()

        // then
        assertThat(ableReceivedCard).isTrue
    }
}
