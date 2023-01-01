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
        assertThat(cards.cards.size).isEqualTo(1)
    }

    @Test
    @DisplayName("조건값 보다 작으면 카드를 뽑을 수 있다")
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
    @DisplayName("조건값 보다 크면 카드를 뽑을 수 없다")
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
    @DisplayName("ACE를 갖고있지 않다면 모든 수를 더해준다")
    fun `sut add all numbers if don't have ACE`() {
        // Arrange
        val defeatedMaxNumber = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))

        // Act
        val summed = cards.sumOfNumbers(defeatedMaxNumber)

        // Assert
        assertThat(summed).isEqualTo(30)
    }

    @Test
    @DisplayName("ACE를 갖고있다면 defeatedMaxNumber를 넘기지 않는 선에서 ACE를 11로 취급해서 모든 수를 더해준다")
    fun `sut add all numbers if have ACE then plus 10`() {
        // Arrange
        val defeatedMaxNumber = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.ACE, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TWO, PokerShape.CLOVER))

        // Act
        val summed = cards.sumOfNumbers(defeatedMaxNumber)

        // Assert
        assertThat(summed).isEqualTo(13)
    }
}
