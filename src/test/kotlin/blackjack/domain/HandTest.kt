package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class HandTest {
    @Test
    fun `Ace 이외의 카드들의 합을 구한다`() {
        val hand = createHand(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        hand.value() shouldBe 2 + 3 + 4 + 5
    }

    @Test
    fun `Ace 를 제외한 카드들의 합이 10 미만이면 Ace 값이 11이다`() {
        val hand = createHand(Rank.NINE, Rank.ACE)
        hand.value() shouldBe 9 + 11
    }

    @Test
    fun `Ace 를 제외한 카드들의 합이 10일 때 Ace 값이 11이다`() {
        val hand = createHand(Rank.TWO, Rank.EIGHT, Rank.ACE)
        hand.value() shouldBe 2 + 8 + 11
    }

    @Test
    fun `Ace 를 제외한 카드들의 합이 11 이상일 떼, Ace 값은 1이다`() {
        val hand = createHand(Rank.TWO, Rank.NINE, Rank.ACE)
        hand.value() shouldBe 2 + 9 + 1
    }

    @Test
    fun `덱에서 카드를 뽑을 수 있다`() {
        val hand = Hand()
        val deck = StubDeck.from(Rank.TWO)

        hand.drawFrom(deck)

        hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
    }

    private fun createHand(vararg ranks: Rank): Hand =
        Hand(
            ranks.map { Card.of(DUMMY_SUIT, it) },
        )

    companion object {
        private val DUMMY_SUIT = Suit.SPADES
    }
}
