package blackjack.domain

import blackjack.model.DEFAULT_CARD_DECK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러는 총 52장의 카드 덱을 가지고 게임을 시작해야 한다`() {
        assertThat(Dealer().deck.size).isEqualTo(52)
    }

    @Test
    fun `딜러는 게임 시작 전 카드덱을 셔플 한다`() {
        val deck = CardDeck(DEFAULT_CARD_DECK)
        val dealer = Dealer(deck).apply { shuffle() }
        assertThat(dealer.deck).isEqualTo(deck)
    }

    @Test
    fun `딜러는 플레이어에게 카드 한장을 전달 할 수 있다`() {
        val (cardDeck, firstCard) = DEFAULT_CARD_DECK to DEFAULT_CARD_DECK.first()
        val resultCount = cardDeck.size - 1
        val dealer = Dealer(CardDeck(cardDeck))
        assertThat(dealer.deliverCard()).isEqualTo(firstCard)
        assertThat(dealer.deck.size).isEqualTo(resultCount)
    }
}
