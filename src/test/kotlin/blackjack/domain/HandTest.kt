package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

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

    @ParameterizedTest
    @MethodSource
    fun `블랙잭인지 판별한다`(
        hand: Hand,
        expected: Boolean,
    ) {
        hand.isBlackjack() shouldBe expected
    }

    @ParameterizedTest
    @MethodSource
    fun `Bust 인지 판별한다`(
        hand: Hand,
        expected: Boolean,
    ) {
        hand.isBusted() shouldBe expected
    }

    companion object {
        private val DUMMY_SUIT = Suit.SPADES

        private fun createHand(vararg ranks: Rank): Hand =
            Hand(
                ranks.map { Card.of(DUMMY_SUIT, it) },
            )

        @JvmStatic
        private fun `블랙잭인지 판별한다`(): List<Arguments> =
            listOf(
                Arguments.of(createHand(Rank.ACE, Rank.KING), true),
                Arguments.of(createHand(Rank.ACE, Rank.QUEEN), true),
                Arguments.of(createHand(Rank.ACE, Rank.JACK), true),
                Arguments.of(createHand(Rank.ACE, Rank.TWO, Rank.EIGHT), false),
                Arguments.of(createHand(Rank.TWO, Rank.FOUR), false),
                Arguments.of(createHand(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE), false),
            )

        @JvmStatic
        private fun `Bust 인지 판별한다`(): List<Arguments> =
            listOf(
                // blackjack
                Arguments.of(createHand(Rank.ACE, Rank.KING), false),
                // 21
                Arguments.of(createHand(Rank.ACE, Rank.TWO, Rank.EIGHT), false),
                // 22
                Arguments.of(createHand(Rank.TEN, Rank.JACK, Rank.TWO), true),
                Arguments.of(createHand(Rank.SIX, Rank.SEVEN, Rank.NINE), true),
            )
    }
}