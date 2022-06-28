package blackjack

import blackjack.domain.card.*
import blackjack.domain.player.Player
import blackjack.domain.player.UserRole
import org.assertj.core.api.Assertions
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
        val blackjackGameElement = BlackjackGameElement(listOf(Player("성주")), testDeck)

        assertThat(blackjackGameElement.gamers[0].cards.size).isEqualTo(2)
    }

    @Test
    fun `card Draw 시 카드 확인`() {
        val blackjackGameElement = BlackjackGameElement(listOf(Player("성주")), testDeck)

        assertThat(blackjackGameElement.draw()).isEqualTo(Card(CardShape.SPADE, CardSymbol.JACK))
    }

    @Test
    fun `카드보다 플레이어가 많을 경우 예외처리`() {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { BlackjackGameElement(getTestUsers(), testDeck) }
    }


    private fun getTestUsers(): List<UserRole> {
        return listOf(
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
            Player("testUser"),
        )
    }
}
