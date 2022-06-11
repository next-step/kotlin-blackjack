package camp.nextstep.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardTest {

    @DisplayName("카드는 모양과 숫자로 이루어져있다.")
    @Test
    fun cardPropertiesTest() {
        val spadeAce = Card.of(CardSuit.SPADE, CardNumber.ACE)

        val (suit, cardNumber) = spadeAce

        assertThat(suit).isEqualTo(CardSuit.SPADE)
        assertThat(cardNumber).isEqualTo(CardNumber.ACE)
    }

    @DisplayName("카드 모양과 숫자가 같으면 같은 카드이다.")
    @Test
    fun cardEqualityTest() {
        val spadeAce1 = Card.of(CardSuit.SPADE, CardNumber.ACE)
        val spadeAce2 = Card.of(CardSuit.SPADE, CardNumber.ACE)

        assertThat(spadeAce1).isEqualTo(spadeAce2)
    }

    @DisplayName("카드 조합은 총 52가지이다.")
    @Test
    fun cardCombinationNumberTest() {
        val allCombinationsOfCards = Card.ofCombinations()

        assertThat(allCombinationsOfCards.size).isEqualTo(CardSuit.CARD_SUIT_NUMBERS * CardNumber.CARD_NUMBERS)
    }

    @DisplayName("카드 조합 테스트")
    @ParameterizedTest
    @CsvSource(
        delimiter = ',',
        value = [
            "SPADE,ACE", "SPADE,TWO", "SPADE,THREE", "SPADE,FOUR", "SPADE,FIVE", "SPADE,SIX", "SPADE,SEVEN", "SPADE,EIGHT", "SPADE,NINE", "SPADE,TEN", "SPADE,JACK", "SPADE,QUEEN", "SPADE,KING",
            "HEART,ACE", "HEART,TWO", "HEART,THREE", "HEART,FOUR", "HEART,FIVE", "HEART,SIX", "HEART,SEVEN", "HEART,EIGHT", "HEART,NINE", "HEART,TEN", "HEART,JACK", "HEART,QUEEN", "HEART,KING",
            "DIAMOND,ACE", "DIAMOND,TWO", "DIAMOND,THREE", "DIAMOND,FOUR", "DIAMOND,FIVE", "DIAMOND,SIX", "DIAMOND,SEVEN", "DIAMOND,EIGHT", "DIAMOND,NINE", "DIAMOND,TEN", "DIAMOND,JACK", "DIAMOND,QUEEN", "DIAMOND,KING",
            "CLUB,ACE", "CLUB,TWO", "CLUB,THREE", "CLUB,FOUR", "CLUB,FIVE", "CLUB,SIX", "CLUB,SEVEN", "CLUB,EIGHT", "CLUB,NINE", "CLUB,TEN", "CLUB,JACK", "CLUB,QUEEN", "CLUB,KING",
        ]
    )
    fun getCardTest(suit: String, number: String) {
        val actualCardCombinations = Card.ofCombinations()

        val cardSuit = CardSuit.valueOf(suit)
        val cardNumber = CardNumber.valueOf(number)
        val expectedCard = Card.of(cardSuit, cardNumber)

        assertThat(actualCardCombinations.contains(expectedCard)).isTrue
    }
}
