package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 더미에서 카드를 뽑을 수 있다`() {
        val cards = listOf(Card(CardNumber.ACE, CardSuit.HEART))
        val cardDeck = CardDeck.from(cards)

        cardDeck.pickCard() shouldBe Card(CardNumber.ACE, CardSuit.HEART)
    }
}
