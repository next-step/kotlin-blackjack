package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ScoreCalculatorTest {

    @Test
    @DisplayName("ACE를 갖고있지 않다면 모든 수를 더해준다")
    fun `sut add all numbers if don't have ACE`() {
        // Arrange
        val blackJack = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))
        val sut = ScoreCalculator(cards)

        // Act
        val actual = sut.sumOfNumbers(blackJack)

        // Assert
        assertThat(actual).isEqualTo(30)
    }

    @Test
    @DisplayName("ACE를 갖고있다면 blackJack을 넘기지 않는 선에서 ACE를 11로 취급해서 모든 수를 더해준다")
    fun `sut add all numbers if have ACE then plus 10`() {
        // Arrange
        val blackJack = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.ACE, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TWO, PokerShape.CLOVER))
        val sut = ScoreCalculator(cards)

        // Act
        val actual = sut.sumOfNumbers(blackJack)

        // Assert
        assertThat(actual).isEqualTo(13)
    }

    @Test
    @DisplayName("10클로버와 10다이아몬드를 가진 상태에서 ACE다이아몬드를 받으면 21점이다")
    fun `sut add all numbers if have 10,10,ACE then 21`() {
        // Arrange
        val blackJack = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.ACE, PokerShape.DIAMOND))
        val sut = ScoreCalculator(cards)

        // Act
        val actual = sut.sumOfNumbers(blackJack)

        // Assert
        assertThat(actual).isEqualTo(21)
    }

    @Test
    @DisplayName("10클로버와 4다이아를 가진 상태에서 ACE다이아몬드를 받으면 15점이다")
    fun `sut add all numbers if have 10,4,ACE then 15`() {
        // Arrange
        val blackJack = 21
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.FOUR, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.ACE, PokerShape.DIAMOND))
        val sut = ScoreCalculator(cards)

        // Act
        val actual = sut.sumOfNumbers(blackJack)

        // Assert
        assertThat(actual).isEqualTo(15)
    }
}
