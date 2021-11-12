package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 덱 테스트")
class DeckTest {

    @Test
    @DisplayName("한벌의 덱을 만들 수 있다")
    fun `sut returns deck`() {
        // Act
        val deck = Deck.init()

        // Assert
        assertThat(deck.size()).isEqualTo(52)
    }

    @Test
    @DisplayName("덱에서 한장의 카드를 꺼낼 수 있다")
    fun `sut returns take out a card`() {
        // Arrange
        val deck = Deck.init()

        // Act
        val card = deck.takeOut()

        // Assert
        assertThat(deck.size()).isEqualTo(51)
        assertThat(card.getScore()).isGreaterThanOrEqualTo(1)
    }
}
