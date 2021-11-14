package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("FirstDraw 테스트")
class FirstDrawTest {

    @Test
    @DisplayName("카드를 한장 뽑을 경우 Deal 상태로 변경된다")
    fun `sut returns deal state`() {
        // Arrange
        val card = Card(Denomination.SEVEN, Suit.DIAMOND)
        val cards = Cards()

        // Act
        val sut = FirstDraw(cards)
        val nextState = sut.draw(card)

        // Assert
        assertThat(nextState.isFinished()).isFalse
        assertThat(nextState).isInstanceOf(Deal::class.java)
    }
}
