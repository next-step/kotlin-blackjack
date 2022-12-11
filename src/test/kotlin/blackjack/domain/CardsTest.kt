package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardsTest {

    @DisplayName("카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun cardDeck(cardDeckCount: Int) {
        assertThat(Cards.CARD_DECK.size).isEqualTo(cardDeckCount)
    }
}
