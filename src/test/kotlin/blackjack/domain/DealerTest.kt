package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    @DisplayName("딜러는 총 52장의 카드 덱을 가지고 게임을 시작해야 한다.")
    @Test
    fun deck() {
        assertThat(Dealer().deck.size).isEqualTo(52)
    }

    @DisplayName("딜러는 게임 시작 전 카드덱을 셔플 한다.")
    @Test
    fun shuffle() {
        val deck = Cards(DEFAULT_CARD_DECK)
        val dealer = Dealer(deck).apply { shuffle() }
        assertThat(dealer.deck).isEqualTo(deck)
    }

    @DisplayName("딜러는 플레이어에게 카드 한장을 전달 할 수 있다.")
    @Test
    fun deliverCard() {
        val (cardDeck, firstCard) = DEFAULT_CARD_DECK to DEFAULT_CARD_DECK.first()
        val deck = Cards(cardDeck)
        val dealer = Dealer(deck).apply { shuffle() }
        assertThat(dealer.deliverCard()).isEqualTo(firstCard)
    }
}
