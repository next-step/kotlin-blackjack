package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("카드는 숫자와 문양을 가진다.")
    @Test
    fun numberAndSuit() {
        val number = CardNumber.ACE
        val suit = CardSuit.DIAMOND
        val actual = Card(number, suit)

        actual.number shouldBe number
        actual.suit shouldBe suit
    }
}
