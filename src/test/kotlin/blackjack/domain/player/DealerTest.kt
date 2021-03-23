package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.SORTED_SHUFFLE
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DealerTest {
    @DisplayName("카드의 합이 16 이하인 경우 True 반환")
    @ParameterizedTest
    @MethodSource("provideUnderSixteen")
    fun canDraw(card1: Card, card2: Card, card3: Card) {
        val dealer = Dealer()
        dealer.draw(card1, DrawDecider.DRAW)
        dealer.draw(card2, DrawDecider.DRAW)
        dealer.draw(card3, DrawDecider.DRAW)

        assertThat(dealer.canDraw()).isTrue
    }

    @DisplayName("카드의 합이 16이 넘는 경우 False 반환")
    @ParameterizedTest
    @MethodSource("provideOverSixteen")
    fun canDraw2(card1: Card, card2: Card, card3: Card) {
        val dealer = Dealer()
        dealer.draw(card1, DrawDecider.DRAW)
        dealer.draw(card2, DrawDecider.DRAW)
        dealer.draw(card3, DrawDecider.DRAW)

        assertThat(dealer.canDraw()).isFalse
    }

    @DisplayName("카드의 합이 16 이하인 경우 카드를 뽑는다")
    @Test
    fun drawAdditional() {
        val dealer = Dealer()
        dealer.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        dealer.draw(createCard(CardSymbol.FIVE.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        dealer.drawAdditional(CardDeck(SORTED_SHUFFLE))

        assertThat(dealer.hands.cards.size).isEqualTo(3)
    }

    companion object {
        @JvmStatic
        fun provideOverSixteen() = listOf(cardArgumentsOf(CardSymbol.TWO, CardSymbol.FIVE, CardSymbol.KING))

        @JvmStatic
        fun provideUnderSixteen() = listOf(cardArgumentsOf(CardSymbol.TWO, CardSymbol.FOUR, CardSymbol.KING))

        private fun cardArgumentsOf(vararg symbols: CardSymbol) =
            Arguments.of(*symbols.map { Card(it, CardSuit.SPADE) }.toTypedArray())
    }
}
