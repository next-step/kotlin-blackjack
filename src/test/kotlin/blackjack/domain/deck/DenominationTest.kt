package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("끗수 테스트")
class DenominationTest {

    @Test
    @DisplayName("symbol이 A일 경우 ACE로 구분한다")
    fun `sut returns ace`() {
        // Arrange
        val symbol = "A"

        // Act
        val result = Denomination.isAce(symbol)

        // Assert
        assertThat(result).isTrue
    }

    @ParameterizedTest
    @ValueSource(strings = ["K", "Q", "J"])
    @DisplayName("symbol이 K, Q, J일 경우 그림카드로 구분한다")
    fun `sut returns drawing cards`(symbol: String) {
        // Act
        val result = Denomination.isDrawingCard(symbol)

        // Assert
        assertThat(result).isTrue
    }
}
