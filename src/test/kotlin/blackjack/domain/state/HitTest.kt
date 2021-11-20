package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Hit 테스트")
class HitTest {

    @Test
    @DisplayName("카드의 총합이 21이 될 경우 TwentyOne 상태로 변경된다")
    fun `sut returns twenty one state`() {
        // Arrange
        val cardSeven = Card(Denomination.SEVEN, Suit.DIAMOND)
        val cardTen = Card(Denomination.TEN, Suit.DIAMOND)
        val cards = Cards()
        cards.receiveCard(cardSeven)
        cards.receiveCard(cardTen)

        // Act
        val sut = Hit(cards)
        val nextState = sut.draw(Card(Denomination.FOUR, Suit.DIAMOND))

        // Assert
        Assertions.assertThat(nextState.isFinished()).isTrue
        Assertions.assertThat(nextState).isInstanceOf(TwentyOne::class.java)
    }

    @Test
    @DisplayName("카드의 총합이 21을 초과할 경우 Bust 상태로 변경된다")
    fun `sut returns bust state`() {
        // Arrange
        val cardSeven = Card(Denomination.SEVEN, Suit.DIAMOND)
        val cardTen = Card(Denomination.TEN, Suit.DIAMOND)
        val cards = Cards()
        cards.receiveCard(cardSeven)
        cards.receiveCard(cardTen)

        // Act
        val sut = Hit(cards)
        val nextState = sut.draw(Card(Denomination.FIVE, Suit.DIAMOND))

        // Assert
        Assertions.assertThat(nextState.isFinished()).isTrue
        Assertions.assertThat(nextState).isInstanceOf(Bust::class.java)
    }

    @Test
    @DisplayName("카드의 총합이 21 미만일 경우 다시 Hit를 할 수 있다")
    fun `sut returns hit state`() {
        // Arrange
        val cardSeven = Card(Denomination.SEVEN, Suit.DIAMOND)
        val cardTen = Card(Denomination.TEN, Suit.DIAMOND)
        val cards = Cards()
        cards.receiveCard(cardSeven)
        cards.receiveCard(cardTen)

        // Act
        val sut = Hit(cards)
        val nextState = sut.draw(Card(Denomination.TWO, Suit.DIAMOND))

        // Assert
        Assertions.assertThat(nextState.isFinished()).isFalse
        Assertions.assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
