package camp.nextstep.blackjack.card

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardsTest {

    @DisplayName("카드 조합은 총 52가지이다.")
    @Test
    fun cardCombinationNumber() {
        val allCombinationsOfCards = Cards.cards

        Assertions.assertThat(allCombinationsOfCards.size).isEqualTo(CardSuit.CARD_SUIT_NUMBERS * CardNumber.CARD_NUMBERS)
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
    fun getCard(suit: String, number: String) {
        val actualCardCombinations = Cards.cards

        val cardSuit = CardSuit.valueOf(suit)
        val cardNumber = CardNumber.valueOf(number)
        val expectedCard = Cards.of(cardSuit, cardNumber)

        Assertions.assertThat(actualCardCombinations.contains(expectedCard)).isTrue
    }
}
