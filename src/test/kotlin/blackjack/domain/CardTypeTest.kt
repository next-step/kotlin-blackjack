package blackjack.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.06.11..
 */
class CardTypeTest {
    @ParameterizedTest
    @ValueSource(strings = ["나무", "바람", "불"])
    fun `카드타입에 없는 타입을 가져오는 경우 IllegalArgumentException을 던진다`(source: String) {
        assertThrows<IllegalArgumentException> {
            CardType.pick(source)
        }
    }
}
