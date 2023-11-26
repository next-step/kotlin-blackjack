package blackjack.domain

import blackjack.domain.BlackjackUtil.DEALER_OPEN_CARD_NUM
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `모든 플레이어에게 초기카드를 2장씩 나눠준다`() {
        val dealer = Dealer()
        val players = listOf(Player("test1"), Player("test2"))
        dealer.supplyInitialCards(players + dealer)

        players.forEach { assertThat(it.hand.size()).isEqualTo(2) }
        assertThat(dealer.hand.size()).isEqualTo(2)
    }

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

        assertThat(player.hand.size()).isEqualTo(allCards.size)
        assertThat(player.initialCards()).isEqualTo(allCards.take(DEALER_OPEN_CARD_NUM))
    }

    @Test
    fun `딜러용 손패 점수 계산으로 카드를 더 뽑을 수 있는지 반환`() {
        val player = Dealer()

        // 16점 이하 (11점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.ACE))
        assertThat(player.canDrawMore()).isTrue()

        // 16점 이하 (16점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.FIVE))
        assertThat(player.canDrawMore()).isTrue()

        // 16점 초과 (18점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.TWO))
        assertThat(player.canDrawMore()).isFalse()
    }
}
