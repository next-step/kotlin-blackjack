package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardDeckTest {

    @DisplayName("카드 뭉치의 카드 개수는 13 * 4인 52장이다.")
    @Test
    fun cardDeckSize() {
        val actual = CardDeck().size()
        val expect = CardSuit.values().size * CardRank.values().size

        actual shouldBe expect
    }
}
