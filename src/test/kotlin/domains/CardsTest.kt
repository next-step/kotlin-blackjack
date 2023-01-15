package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    @DisplayName("cards에 card를 추가할 수 있다")
    fun `sut add card`() {
        // Arrange
        val cards = Cards()

        // Act
        cards.addCard(Card(PokerNumber.ACE, PokerShape.CLOVER))

        // Assert
        assertThat(cards.values.size).isEqualTo(1)
    }

    @Test
    @DisplayName("현재 가진 카드의 합이 조건값 보다 작으면 카드를 뽑을 수 있다")
    fun `sut drawable if less than condition value`() {
        // Arrange
        val condition = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.ACE, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TWO, PokerShape.CLOVER))

        // Act
        val isDrawable = cards.isDrawable(condition)

        // Assert
        assertThat(isDrawable).isTrue
    }

    @Test
    @DisplayName("현재 가진 카드의 합이 조건값 보다 크면 카드를 뽑을 수 없다")
    fun `sut is not drawable if more than condition value`() {
        // Arrange
        val condition = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))

        // Act
        val isDrawable = cards.isDrawable(condition)

        // Assert
        assertThat(isDrawable).isFalse
    }

    @Test
    @DisplayName("현재 가진 카드의 합이 조건값과 같으면 카드를 뽑을 수 없다")
    fun `sut is not drawable if condition value and sum of value are same`() {
        // Arrange
        val condition = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.ACE, PokerShape.HEART))

        // Act
        val isDrawable = cards.isDrawable(condition)

        // Assert
        assertThat(isDrawable).isFalse
    }
}
