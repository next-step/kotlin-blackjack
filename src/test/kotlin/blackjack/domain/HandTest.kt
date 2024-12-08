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

        hand[0] shouldBe Card(StubDeck.DUMMY_SUIT, Rank.TWO)
    }

    @ParameterizedTest(name = "{index} 핸드 = {2}")
    @MethodSource
    fun `블랙잭인지 판별한다`(
        hand: Hand,
        expected: Boolean,
        description: String,
    ) {
        hand.isBlackjack() shouldBe expected
    }

    @ParameterizedTest(name = "{index} 핸드 = {2}")
    @MethodSource
    fun `Bust 인지 판별한다`(
        hand: Hand,
        expected: Boolean,
        description: String,
    ) {
        hand.isBusted() shouldBe expected
    }

    @ParameterizedTest(name = "{index} {3}")
    @MethodSource
    fun `딜러와 부승부다`(
        hand: Hand,
        dealerHand: DealerHand,
        expected: Boolean,
        description: String,
    ) {
        hand.pushes(dealerHand) shouldBe expected
    }

    @ParameterizedTest(name = "{index} {3}")
    @MethodSource
    fun `딜러를 이긴다`(
        hand: Hand,
        dealerHand: DealerHand,
        expected: Boolean,
        description: String,
    ) {
        hand.beats(dealerHand) shouldBe expected
    }

    companion object {
        private val DUMMY_SUIT = Suit.SPADES

        private fun createHand(vararg ranks: Rank): Hand =
            Hand(
                ranks.map { Card(DUMMY_SUIT, it) },
            )

        private fun createDealerHand(vararg ranks: Rank): DealerHand {
            val deck = StubDeck.from(*ranks)
            return DealerHand().apply {
                repeat(ranks.size) {
                    drawFrom(deck)
                }
            }
        }

        @JvmStatic
        private fun `블랙잭인지 판별한다`(): List<Arguments> =
            listOf(
                Arguments.of(createHand(Rank.ACE, Rank.KING), true, "A, K"),
                Arguments.of(createHand(Rank.ACE, Rank.QUEEN), true, "A, Q"),
                Arguments.of(createHand(Rank.ACE, Rank.JACK), true, "A, J"),
                Arguments.of(createHand(Rank.ACE, Rank.TWO, Rank.EIGHT), false, "A, 2, 8"),
                Arguments.of(createHand(Rank.TWO, Rank.FOUR), false, "2, 4"),
                Arguments.of(createHand(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE), false, "2, 3, 4, 5"),
            )

        @JvmStatic
        private fun `Bust 인지 판별한다`(): List<Arguments> =
            listOf(
                // blackjack
                Arguments.of(createHand(Rank.ACE, Rank.KING), false, "A, K"),
                // 21
                Arguments.of(createHand(Rank.ACE, Rank.TWO, Rank.EIGHT), false, "A, 2, 8"),
                // 22
                Arguments.of(createHand(Rank.TEN, Rank.JACK, Rank.TWO), true, "10, J, 2"),
                Arguments.of(createHand(Rank.SIX, Rank.SEVEN, Rank.NINE), true, "6, 7, 9"),
            )

        @JvmStatic
        private fun `딜러와 부승부다`(): List<Arguments> =
            listOf(
                Arguments.of(
                    createHand(Rank.ACE, Rank.KING),
                    createDealerHand(Rank.ACE, Rank.QUEEN),
                    true,
                    "플레이어 = 블랙잭, 딜러 = 블랙잭",
                ),
                Arguments.of(
                    createHand(Rank.TWO, Rank.SEVEN),
                    createDealerHand(Rank.FOUR, Rank.FIVE),
                    true,
                    "플레이어 = 9, 딜러 = 9",
                ),
                Arguments.of(
                    createHand(Rank.TWO, Rank.SEVEN),
                    createDealerHand(Rank.FOUR, Rank.FIVE, Rank.SIX),
                    false,
                    "플레이어 = 9, 딜러 = 15",
                ),
                Arguments.of(
                    createHand(Rank.FOUR, Rank.FIVE, Rank.SIX),
                    createDealerHand(Rank.TWO, Rank.SEVEN),
                    false,
                    "플레이어 = 15, 딜러 = 9",
                ),
            )

        @JvmStatic
        private fun `딜러를 이긴다`(): List<Arguments> =
            listOf(
                Arguments.of(
                    createHand(Rank.TEN, Rank.FIVE),
                    createDealerHand(Rank.EIGHT, Rank.SIX),
                    true,
                    "플레이어 = 15, 딜러 = 14",
                ),
                Arguments.of(
                    createHand(Rank.EIGHT, Rank.SIX),
                    createDealerHand(Rank.TEN, Rank.FIVE),
                    false,
                    "플레이어 = 14, 딜러 = 15",
                ),
            )
    }
}
