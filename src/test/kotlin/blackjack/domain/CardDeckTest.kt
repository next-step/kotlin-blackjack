package blackjack.domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardDeckTest {
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun `카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성할 수 있다`(cardDeckCount: Int) {
    }
}
