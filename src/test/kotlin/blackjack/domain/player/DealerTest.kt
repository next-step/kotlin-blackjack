package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    @DisplayName("카드의 합이 16 이하인 경우 True 반환")
    @Test
    fun canDraw() {
        val dealer = Dealer()
        dealer.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        dealer.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        dealer.draw(createCard(CardSymbol.FOUR.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        Assertions.assertThat(dealer.canDraw()).isTrue
    }

    @DisplayName("카드의 합이 16이 넘는 경우 False 반환")
    @Test
    fun canDraw2() {
        val dealer = Dealer()
        dealer.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        dealer.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        dealer.draw(createCard(CardSymbol.FIVE.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        Assertions.assertThat(dealer.canDraw()).isFalse
    }
}
