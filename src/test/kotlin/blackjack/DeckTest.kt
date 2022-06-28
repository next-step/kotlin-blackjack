package blackjack

import blackjack.domain.card.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `덱을 처음 생성 시 카드를 52개 가진다`() {
        val deck = Deck(BlackjackCardShuffle())
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `요청 값에 맞는 카드를 가져온다`() {
        val cardShuffle = object : CardShuffle {
            override fun getCards(): List<Card> {
                return listOf(Card(CardShape.SPADE, CardSymbol.ACE))
            }
        }
        val deck = Deck(cardShuffle)
        val card = deck.deal()
        assertThat(card).isEqualTo(Card(CardShape.SPADE, CardSymbol.ACE))
    }

    @Test
    fun `카드를 가져온 후 남은 카드 갯 수 확인한다`() {
        val deck = Deck(BlackjackCardShuffle())
        deck.deal()
        assertThat(deck.cards.size).isEqualTo(51)
    }
}
