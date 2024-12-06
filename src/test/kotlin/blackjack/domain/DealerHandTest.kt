package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
        val deck = StubDeck.from(Rank.FOUR, Rank.FIVE)

        hand.drawFrom(deck)
        hand.drawFrom(deck)

        hand[0].isFaceUp shouldBe true
        hand[1].isFaceUp shouldBe false
    }

    @Test
    fun `두번쪠 카드의 앞면이 보이도록 뒤집을 수 있다`() {
        val deck = StubDeck.from(Rank.SIX, Rank.SEVEN)
        val hand =
            DealerHand().apply {
                drawFrom(deck)
                drawFrom(deck)
            }

        hand.flipHoleCardUp()

        hand[1].isFaceUp shouldBe true
    }

    @Test
    fun `이미 카드가 앞면으로 뒤집어져 있으면 예외를 던진다`() {
        val deck = StubDeck.from(Rank.EIGHT, Rank.NINE)
        val hand =
            DealerHand().apply {
                drawFrom(deck)
                drawFrom(deck)
                flipHoleCardUp()
            }
        assertThrows<IllegalStateException> { hand.flipHoleCardUp() }
    }

    @Test
    fun `두번째 카드가 없으면 예외를 던진다`() {
        val hand = DealerHand()
        assertThrows<IllegalStateException> { hand.flipHoleCardUp() }
    }
}
