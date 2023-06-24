package blackjack.card

import domain.card.util.DeckGenerator
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DeckGeneratorTest {

    @ParameterizedTest
    @CsvSource(
        "1, 52",
        "2, 104",
        "6, 312",
    )
    fun `덱은 사이즈에 따라 카드 개수가 달라진다`(decSize: Int, expectedCardCount: Int) {
        val deck = DeckGenerator.makeDeck(decSize)
        deck.cardCount shouldBe expectedCardCount
    }
}
