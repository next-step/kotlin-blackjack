package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DealerHandTest {
    @Test
    fun `카드를 덱에서 뽑을 수 있다`() {
        val hand = DealerHand()
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)

        hand.drawFrom(deck)

        hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
    }

    @Test
    fun `두번쩨 카드는 뒷면이 보인다`() {
        val hand = DealerHand()
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)

        hand.drawFrom(deck)
        hand.drawFrom(deck)

        hand[1].isFaceUp shouldBe false
    }
}
