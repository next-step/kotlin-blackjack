package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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
}
