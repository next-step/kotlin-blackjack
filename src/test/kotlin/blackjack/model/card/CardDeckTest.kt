package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardDeckTest {
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun beforeTest() {
        cardDeck = CardDeck()
    }

    @DisplayName(value = "게임의 초기화(카드 덱 생성)를 확인한다.")
    @Test
    fun generateCardDeck() {
        assertThat(cardDeck.cards.getCount()).isEqualTo(52)
    }

    @Test
    fun drawCard() {
        cardDeck.pick()
        assertThat(cardDeck.cards.getCount()).isEqualTo(51)
        cardDeck.pick()
        assertThat(cardDeck.cards.getCount()).isEqualTo(50)
    }
}
