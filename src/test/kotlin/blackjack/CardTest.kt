package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource("Ace,Spade", "Ten,Diamond", "Jack,Heart", "King,Club")
    fun `카드는 Number와 Suit 을 속성으로 갖는다`(cardNumber: CardNumber, suit: Suit) {
        assertThat(Card(cardNumber, suit).cardNumber).isEqualTo(cardNumber)
        assertThat(Card(cardNumber, suit).suit).isEqualTo(suit)
    }
}
