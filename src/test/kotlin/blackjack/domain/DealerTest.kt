package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    val under16Cards = Cards(
        arrayListOf(
            Card(Number.FIVE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND),
            Card(Number.ACE to Pattern.DIAMOND)
        )
    )
    val over17Cards = Cards(
        arrayListOf(
            Card(Number.FIVE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND),
            Card(Number.FIVE to Pattern.DIAMOND)
        )
    )

    @Test
    fun `딜러의 카드가 16이하인 경우 카드 1장을 더 받는다`() {
        val dealer = Dealer(cards = under16Cards)
        dealer.drawCard()
        assertThat(dealer.cards.getCardList().size).isEqualTo(4)
    }

    @Test
    fun `딜러의 카드가 17 이상인 경우 카드를 받지 않는다`() {
        val dealer = Dealer(cards = over17Cards)
        dealer.drawCard()
        assertThat(dealer.cards.getCardList().size).isEqualTo(3)
    }
}
