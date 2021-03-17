package blackjack.domain.player

import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import blackjack.domain.createPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @DisplayName("플레이어가 카드를 인자로 받은 경우 카드 목록에 추가")
    @Test
    fun draw() {
        val player = createPlayer("pobi")
        val card = createCard(CardSymbol.ACE.name, CardSuit.SPADE.name)

        player.draw(card)

        assertThat(player.hands.cards).isEqualTo(listOf(card))
    }

    @DisplayName("카드의 합이 21이 넘지 않는 경우 True 반환")
    @Test
    fun canDraw() {
        val player = createPlayer("pobi")
        player.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name))
        player.draw(createCard(CardSymbol.QUEEN.name, CardSuit.SPADE.name))
        player.draw(createCard(CardSymbol.ACE.name, CardSuit.SPADE.name))

        assertThat(player.canDraw()).isTrue
    }

    @DisplayName("카드의 합이 21이 넘는 경우 False 반환")
    @Test
    fun canDraw2() {
        val player = createPlayer("pobi")
        player.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name))
        player.draw(createCard(CardSymbol.QUEEN.name, CardSuit.SPADE.name))
        player.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name))

        assertThat(player.canDraw()).isFalse
    }
}
