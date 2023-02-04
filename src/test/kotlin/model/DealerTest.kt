package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class DealerTest {
    @Test
    fun `딜러 카드 숫자 합이 16이하일 경우 추가 카드 받기가 가능하다`() {
        assertAll(
            {
                val dealer = Dealer("딜러")
                dealer.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))
                dealer.receiveCard(Card(CardNumber.FIVE, CardShape.HEARTS))
                assertThat(
                    dealer.isExtraCard()
                ).isTrue
            },
            {
                val dealer = Dealer("딜러")
                dealer.receiveCard(Card(CardNumber.TEN, CardShape.HEARTS))
                dealer.receiveCard(Card(CardNumber.SIX, CardShape.CLUBS))
                assertThat(
                    dealer.isExtraCard()
                ).isTrue
            }
        )
    }

    @Test
    fun `딜러 카드 숫자 합이 16초과일 경우 추가 카드 받기가 불가능하다`() {
        assertAll(
            {
                val dealer = Dealer("딜러")
                dealer.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))
                dealer.receiveCard(Card(CardNumber.SEVEN, CardShape.HEARTS))
                assertThat(
                    dealer.isExtraCard()
                ).isFalse
            },
            {
                val dealer = Dealer("딜러")
                dealer.receiveCard(Card(CardNumber.TEN, CardShape.HEARTS))
                dealer.receiveCard(Card(CardNumber.EIGHT, CardShape.CLUBS))
                assertThat(
                    dealer.isExtraCard()
                ).isFalse
            }
        )
    }
}
