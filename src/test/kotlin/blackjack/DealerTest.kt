package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {
    private val heartCard = Card(Symbol.HEART, Numbers.FOUR)
    private val clubCard = Card(Symbol.CLUB, Numbers.JACK)
    private val cards = Cards(mutableListOf(heartCard, clubCard))

    @DisplayName("딜러 카드의 합계가 16 이하이면 1장의 카드를 추가로 받는다.")
    @Test
    fun `dealer add card`() {
        val dealer = Dealer(cards)
        dealer.addCards(Card(Symbol.HEART, Numbers.FOUR))
        assertThat(dealer.cards).isEqualTo(Cards(mutableListOf(heartCard, clubCard, Card(Symbol.HEART, Numbers.FOUR))))
    }
}
