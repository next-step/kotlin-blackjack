package blackjack.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {

    @ParameterizedTest
    @CsvSource(value = ["SPADES|10", "HEARTS|8"], delimiterString = "|")
    fun `원하는 카드가 생성되는지 확인한다`(suit: String, denomination: Int) {
        val card = Card(suit, denomination)
        assertThat(card.suit).isEqualTo(suit)
        assertThat(card.denomination).isEqualTo(denomination)
    }
}
