package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsTest {
    @Test
    fun `카드들을 속성으로 갖는다`() {
        val cardList = listOf(Card(CardNumber.Ace, Suit.Club), Card(CardNumber.Two, Suit.Heart))
        assertThat(Cards(cardList).values).isSameAs(cardList)
    }

    @Test
    fun `동일한 카드를 한장 이상 가질 수 없다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            Cards(listOf(Card(CardNumber.Ace, Suit.Club), Card(CardNumber.Ace, Suit.Club)))
        }
    }

    @ParameterizedTest
    @CsvSource("Two,,,2,", "Two,Three,,5,", "Two,Ten,Four,16,", "Two,Ten,Jack,22,", "Ace,,,1,11", "Two,Ace,,3,13")
    fun `지급받은 카드들의 숫자 합을 반환한다 (A는 1, 11로 대응된다)`(
        cardNumber1: CardNumber,
        cardNumber2: CardNumber?,
        cardNumber3: CardNumber?,
        score1: Int,
        score2: Int?,
    ) {
        val cardList = listOf(cardNumber1, cardNumber2, cardNumber3)
            .filterNotNull()
            .map { Card(it, Suit.Heart) }
        val scores = listOf(score1, score2).filterNotNull()
        assertThat(Cards(cardList).scores).isEqualTo(scores)
    }
}
