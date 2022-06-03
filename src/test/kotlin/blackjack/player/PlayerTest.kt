package blackjack.player

import blackjack.card.Card
import blackjack.card.CardSuit
import blackjack.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `플레이어는 카드의 점수가 21을 초과하면 BUST 된다`() {
        // given
        val player = Player("pug")

        // when
        player.addCards(DIAMOND_ACE, CLUB_KING, SPADE_TEN, HEART_TWO)

        // then
        assertThat(player.status).isEqualTo(PlayerStatus.BUST)
    }

    @Test
    fun `플레이어는 카드의 점수가 21을 초과하지 않으면 BUST 되지 않는다`() {
        // given
        val player = Player("pug")

        // when
        player.addCards(DIAMOND_ACE, CLUB_KING, SPADE_TEN)

        // then
        assertThat(player.status).isNotEqualTo(PlayerStatus.BUST)
    }

    companion object {
        val DIAMOND_ACE = Card(CardSuit.DIAMOND, CardSymbol.ACE)
        val CLUB_KING = Card(CardSuit.CLUB, CardSymbol.KING)
        val SPADE_TEN = Card(CardSuit.SPADE, CardSymbol.TEN)
        val HEART_TWO = Card(CardSuit.HEART, CardSymbol.TWO)
    }
}
