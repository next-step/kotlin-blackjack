package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 테스트")
class CardTest {

    @Test
    @DisplayName("카드 생성")
    fun `sut returns card`() {
        // Arrange
        val denomination = Denomination.FOUR
        val suit = Suit.SPADE

        // Act
        val card = Card(denomination, suit)

        // Assert
        assertThat(card.toString()).isEqualTo("4스페이드")
        assertThat(card.getScore()).isEqualTo(4)
    }
}
