package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {

    @Test
    fun `Dealer는 16점 이하일 때 카드를 받아야 한다`() {
        val dealer = Dealer()
        assertThat(dealer.canDrawCard()).isTrue()
    }

    @MethodSource("provideCardsAndExpectedResult")
    @ParameterizedTest
    fun `Dealer는 17점 이상일 때 카드를 받지 않아야 한다`(cards: Cards, expected: Boolean) {
        val dealer = Dealer(cards = cards)
        assertThat(dealer.canDrawCard()).isEqualTo(expected)
    }

    @Test
    fun `Dealer의 카드 총합이 22점 이상이면 Bust이다`() {
        val cards = Cards().apply {
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
        }
        val dealer = Dealer(cards = cards)

        assertThat(dealer.isBust()).isTrue()
    }

    companion object {
        @JvmStatic
        fun provideCardsAndExpectedResult() = listOf(
            // 16점인 경우 카드를 받을 수 있어야 한다.
            Cards().apply {
                add(Card.of(Suit.HEART, Rank.FIVE))
                add(Card.of(Suit.SPADE, Rank.SIX))
            } to true,

            // 17점인 경우 카드를 받을 수 없어야 한다.
            Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.DIAMOND, Rank.SEVEN))
            } to false,

            // 21점인 경우 카드를 받을 수 없어야 한다.
            Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.SPADE, Rank.KING))
            } to false
        ).map { (cards, expected) -> org.junit.jupiter.params.provider.Arguments.of(cards, expected) }
    }
}
