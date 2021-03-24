package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import blackjack.domain.createPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @DisplayName("카드의 합이 21 이하인 경우 True 반환")
    @Test
    fun canDraw() {
        val player = createPlayer("pobi")
        player.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        player.draw(createCard(CardSymbol.QUEEN.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        player.draw(createCard(CardSymbol.ACE.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        assertThat(player.canDraw()).isTrue
    }

    @DisplayName("카드의 합이 21이 넘는 경우 False 반환")
    @Test
    fun canDraw2() {
        val player = createPlayer("pobi")
        player.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        player.draw(createCard(CardSymbol.QUEEN.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        player.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        assertThat(player.canDraw()).isFalse
    }
}
