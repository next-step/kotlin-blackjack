package blackjack.domain

import blackjack.domain.BlackjackUtil.DEALER_OPEN_CARD_NUM
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드 소진시 예외를 던진다`() {
        val dealer = Dealer()
        repeat(52) { dealer.supplyCard(dealer) }
        assertThrows(IllegalStateException::class.java) { dealer.supplyCard(dealer) }
    }

    @Test
    fun `초기 카드 요청시 첫장만 반환`() {
        val player = Dealer()
        val allCards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.ACE),
            Card(CardSuitInfo.SPADE, CardNumberInfo.TWO),
        )

        allCards.forEach { player.hand.add(it) }

        Assertions.assertThat(player.hand.size()).isEqualTo(allCards.size)
        Assertions.assertThat(player.initialCards()).isEqualTo(allCards.take(DEALER_OPEN_CARD_NUM))
    }
}
