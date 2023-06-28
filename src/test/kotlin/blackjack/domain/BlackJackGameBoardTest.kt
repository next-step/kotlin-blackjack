package blackjack.domain

import blackjack.domain.Card.Companion.newCard
import blackjack.domain.Score.Companion.toScore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameBoardTest {

    private fun blackJackGameBoard(players: Set<String> = emptySet(), cardDeck: CardDeck = ShuffledCardDeck()): BlackJackGameBoard {
        return BlackJackGameBoard(players = players, cardDeck = cardDeck)
    }

    @Test
    fun `게임 참여자를 알 수 있다`() {
        // given
        val tPlayers = setOf("park", "sang", "jun")
        val sut = blackJackGameBoard(players = tPlayers)

        // when
        val players: List<PlayerName> = sut.getPlayers()

        // then
        assertThat(players.size).isEqualTo(3)
        assertThat(players[0].name).isEqualTo("park")
        assertThat(players[1].name).isEqualTo("sang")
        assertThat(players[2].name).isEqualTo("jun")
    }

    @Test
    fun `게임 참여자는 카드를 뽑기 전에 아무런 카드가 없는상태로 시작한다`() {
        // given
        val tPlayers = setOf("jun")
        val sut = blackJackGameBoard(players = tPlayers)

        // when
        val junCards: Cards = sut.getPlayerCards(PlayerName("jun"))

        // then
        assertThat(junCards.all().size).isEqualTo(0)
    }

    @Test
    fun `게임 참여자가 카드를 받을 수 있다`() {
        // given
        val tPlayers = setOf("park", "jun")
        val tCard = newCard { CardImage.SPADE with CardLevel.ACE }
        val sut = blackJackGameBoard(players = tPlayers, cardDeck = AllSameCardsDeck(tCard))

        // when
        val junCards: Cards = sut.pickCard(PlayerName("jun"))

        // then
        assertThat(junCards.all().size).isEqualTo(1)
        assertThat(junCards.all().first()).isEqualTo(tCard)
    }

    @Test
    fun `게임 참여자의 현재 점수를 알 수 있다`() {
        // given
        val tPlayers = setOf("jun")
        val tCard = newCard { CardImage.SPADE with CardLevel.FIVE }
        val sut = blackJackGameBoard(players = tPlayers, cardDeck = AllSameCardsDeck(tCard))
        sut.pickCard(PlayerName("jun"))

        // when
        val score = sut.getPlayerScore(PlayerName("jun"))

        // then
        assertThat(score).isEqualTo(5.toScore())
    }
}

private class AllSameCardsDeck(private val card: Card) : CardDeck {
    override fun pickCard(): Card = card

    override fun remainCardsCount(): Int {
        throw NotImplementedError("for testing only implementation")
    }
}
