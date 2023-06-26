package blackjack.domain.card

import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardDeckTest {
    @Test
    fun `인덱스를 이용해 카드덱에서 카드를 뽑을 수 있다`() {
        val cardDeck = CardDeck()
        val index = 1
        val card = cardDeck.cards[1]

        val peekedCard = cardDeck.peekCard(index)

        card shouldBe peekedCard
        cardDeck.cards shouldNotContain card
    }
}
