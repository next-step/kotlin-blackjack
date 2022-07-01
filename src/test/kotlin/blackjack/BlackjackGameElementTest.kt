package blackjack

import blackjack.domain.blackjackgame.BlackjackGameElement
import blackjack.domain.card.*
import blackjack.domain.player.Player
import blackjack.domain.player.UserSetting
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackGameElementTest {

    private val testDeck: Deck = Deck(object : CardShuffle {
        override fun getCards(): List<Card> {
            return listOf(
                Card(CardShape.SPADE, CardSymbol.ACE),
                Card(CardShape.SPADE, CardSymbol.KING),
                Card(CardShape.SPADE, CardSymbol.JACK)
            )
        }
    })

    @Test
    fun `게임 시작 시 카드 2장을 가진다`() {
        val blackjackGameElement = BlackjackGameElement(listOf(Player(UserSetting("성주"))), Decks(listOf(testDeck)))

        assertThat(blackjackGameElement.gamers[0].cards.size).isEqualTo(2)
    }

    @Test
    fun `card Draw 시 카드 확인`() {
        val blackjackGameElement = BlackjackGameElement(listOf(Player(UserSetting("성주"))), Decks(listOf(testDeck)))

        assertThat(blackjackGameElement.draw()).isEqualTo(Card(CardShape.SPADE, CardSymbol.JACK))
    }
}
