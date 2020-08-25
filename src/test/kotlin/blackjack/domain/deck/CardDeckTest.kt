package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardDeckTest {
    private val cardDeck = CardDeck()

    @DisplayName("카드덱이 비면 덱을 자동으로 초기화한다")
    @ParameterizedTest
    @ValueSource(ints = [100])
    fun initCardDeck(count: Int) {
        var card = cardDeck.pop()
        for (i in 0 until count) {
            card = cardDeck.pop()
        }
        assertThat(card).isNotNull
    }
}
