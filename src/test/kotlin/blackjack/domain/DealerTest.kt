package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("NonAsciiCharacters")
class DealerTest {
    @Test
    fun `카드를 덱에서 뽑는다`() {
        val dealer = Dealer()
        val deck = StubDeck.from(Rank.TWO)

        dealer.drawFrom(deck)

        dealer.value shouldBe 2
    }

    @Test
    fun `두번째 카드를 앞면이 보이도록 뒤집는다`() {
        val deck = StubDeck.from(Rank.THREE, Rank.FOUR)
        val dealer =
            Dealer().apply {
                drawFrom(deck)
                drawFrom(deck)
            }

        dealer.flipHoleCardUp()

        dealer.hand[1].isFaceUp shouldBe true
    }

    @ParameterizedTest(name = "{index} 카드 합계 = {2}")
    @MethodSource
    fun `합계가 16인 경우 반드시 카드를 뽑아야 한다`(
        deck: Deck,
        expected: Boolean,
        description: String,
    ) {
        val dealer =
            Dealer().apply {
                drawFrom(deck)
                drawFrom(deck)
            }

        dealer.mustDrawCard() shouldBe expected
    }

    @ParameterizedTest(name = "{index} 카드 = {2}")
    @MethodSource
    fun `블랙잭인지 리턴한다`(
        deck: Deck,
        expected: Boolean,
        description: String,
    ) {
        val dealer =
            Dealer().apply {
                drawFrom(deck)
                drawFrom(deck)
            }
        dealer.isBlackjack shouldBe expected
    }

    @Test
    fun `딜러의 턴 행동을 한다`() {
        val deck = StubDeck.from(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN)
        val dealer =
            Dealer().apply {
                drawFrom(deck)
                drawFrom(deck)
            }

        dealer.takeAction(deck)

        dealer.value shouldBe 18
    }

    companion object {
        @JvmStatic
        fun `합계가 16인 경우 반드시 카드를 뽑아야 한다`(): List<Arguments> =
            listOf(
                Arguments.of(StubDeck.from(Rank.SIX, Rank.TEN), true, "16"),
                Arguments.of(StubDeck.from(Rank.JACK, Rank.SEVEN), false, "17"),
            )

        @JvmStatic
        fun `블랙잭인지 리턴한다`(): List<Arguments> =
            listOf(
                Arguments.of(StubDeck.from(Rank.ACE, Rank.KING), true, "A, K"),
                Arguments.of(StubDeck.from(Rank.QUEEN, Rank.JACK), false, "Q, J"),
            )
    }
}
