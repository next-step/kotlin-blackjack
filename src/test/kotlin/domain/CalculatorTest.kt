package domain

import enum.Rank
import enum.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    @DisplayName("King과 3의 조합으로 점수가 13이 되는지 테스트")
    fun `King과 숫자 카드의 조합으로 점수 계산`() {
        val calculator = Calculator()
        calculator.receiveCard(Card(Suit.HEARTS, Rank.KING))
        calculator.receiveCard(Card(Suit.SPADES, Rank.THREE))
        assertEquals(13, calculator.calculateScore())
    }

    @Test
    @DisplayName("King, 3, Ace의 조합으로 점수가 14가 되는지 테스트")
    fun `King, 숫자 카드, Ace의 조합으로 점수 계산`() {
        val calculator = Calculator()
        calculator.receiveCard(Card(Suit.HEARTS, Rank.KING))
        calculator.receiveCard(Card(Suit.SPADES, Rank.THREE))
        calculator.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        assertEquals(14, calculator.calculateScore())
    }

    @Test
    @DisplayName("두 개의 Ace로 점수가 12가 되는지 테스트")
    fun `두 개의 Ace 카드로 점수 계산`() {
        val calculator = Calculator()
        calculator.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        calculator.receiveCard(Card(Suit.DIAMONDS, Rank.ACE))
        assertEquals(12, calculator.calculateScore())
    }

    @Test
    @DisplayName("Ace, Ace, 9의 조합으로 점수가 21이 되는지 테스트")
    fun `Ace, Ace, 9의 조합으로 21 점수 계산`() {
        val calculator = Calculator()
        calculator.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        calculator.receiveCard(Card(Suit.DIAMONDS, Rank.ACE))
        calculator.receiveCard(Card(Suit.HEARTS, Rank.NINE))
        assertEquals(21, calculator.calculateScore())
    }

    @Test
    @DisplayName("Ace, Ace, 9, 5의 조합으로 점수가 16이 되는지 테스트")
    fun `Ace, Ace, 9, 5의 조합으로 점수 계산`() {
        val calculator = Calculator()
        calculator.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        calculator.receiveCard(Card(Suit.DIAMONDS, Rank.ACE))
        calculator.receiveCard(Card(Suit.HEARTS, Rank.NINE))
        calculator.receiveCard(Card(Suit.SPADES, Rank.FIVE))
        assertEquals(16, calculator.calculateScore())
    }
}
