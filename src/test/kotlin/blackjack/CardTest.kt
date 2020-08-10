package blackjack

import blackjack.domain.Card
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @ParameterizedTest
    @DisplayName("카드 유효성 체크")
    @ValueSource(strings = ["", "$", "Z", "ACE"])
    fun validateCard(value: String) {
        assertThatThrownBy { Card(SuitType.SPADE, ValueType.getValueType(value)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @DisplayName("카드 유효성 체크")
    @ValueSource(ints = [13, 24, -4])
    fun validateCard(value: Int) {
        assertThatThrownBy { Card(SuitType.SPADE, ValueType.getValueType(value)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
