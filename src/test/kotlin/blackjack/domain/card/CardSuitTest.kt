package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardSuitTest {

    @DisplayName("CardSuit은 SPADE, HEART, DIAMOND, CLUB 네가지이다.")
    @Test
    fun cardSuitType() {
        val actual = CardSuit.values()
        val expect = arrayOf(CardSuit.SPADE, CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB)

        actual shouldBe expect
    }
}
