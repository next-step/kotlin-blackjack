package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardsTest {

    @DisplayName("카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun cardDeck(cardDeckCount: Int) {
        assertThat(Cards(DEFAULT_CARD_DECK).value.size).isEqualTo(cardDeckCount)
    }
}
