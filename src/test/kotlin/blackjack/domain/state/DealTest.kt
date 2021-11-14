package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Deal 테스트")
internal class DealTest {

    @Test
    @DisplayName("Deal 상태에서 카드의 합이 21일 경우 Blackjack 상태로 변경된다")
    fun `sut returns blackjack state`() {
        // Arrange
        val card = Card(Denomination.ACE, Suit.DIAMOND)
        val cards = Cards()
        cards.receiveCard(card)

        // Act
        val sut = Deal(cards)
        val nextState = sut.draw(Card(Denomination.KING, Suit.DIAMOND))

        // Assert
        Assertions.assertThat(nextState.isFinished()).isTrue
        Assertions.assertThat(nextState).isInstanceOf(Blackjack::class.java)
    }

    @Test
    @DisplayName("Deal 상태에서 카드를 더 뽑을 경우 Hit 상태로 변경된다")
    fun `sut returns hit state`() {
        // Arrange
        val card = Card(Denomination.ACE, Suit.DIAMOND)
        val cards = Cards()
        cards.receiveCard(card)

        // Act
        val sut = Deal(cards)
        val nextState = sut.draw(Card(Denomination.EIGHT, Suit.DIAMOND))

        // Assert
        Assertions.assertThat(nextState.isFinished()).isFalse
        Assertions.assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
