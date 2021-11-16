package blackjack.domain.player

import blackjack.domain.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayersTest {

    @Test
    fun `플레이어 목록을 생성할 수 있다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val actual = Players(listOf(givenGamer1, givenGamer2))

        assertThat(actual).isNotNull
    }

    @Test
    fun `플레이어 이름을 입력받아 플레이어 목록을 생성할 수 있다`() {
        val givenNames = Names(setOf(Name("player1"), Name("player2")))

        assertThat(Players.createGamers(givenNames)).isNotNull
    }

    @Test
    fun `플레이어 목록에 한 명만 존재하면 예외를 던진다`() {
        val givenGamer1 = Gamer(Name("player1"))

        assertThrows<IllegalArgumentException> { Players(listOf(givenGamer1)) }
    }

    @Test
    fun `플레이어가 덱에서 카드를 받을 수 있다`() {
        val givenDeck = Deck()
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val players = Players(listOf(givenGamer1, givenGamer2))

        val actual = players.receiveCardFromDeck(givenDeck)

        assertThat(actual).allMatch { !it.openCards().isEmpty() }
    }

    @Test
    fun `플레이어가 모두 종료상태라면 true를 아니라면 false를 리턴한다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val players = Players(listOf(givenGamer1, givenGamer2))

        assertThat(players.isAllPlayerTurnOff()).isTrue
    }

    @Test
    fun `플레이어 목록을 준비로 설정하면 바뀌면 준비 상태를 리턴한다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val players = Players(listOf(givenGamer1, givenGamer2))

        val actual = players.turnToReady()

        assertThat(actual.players).allMatch { it.isBurst() }
    }

    @Test
    fun `플레이어의 턴을 종료하면 종료된 상태를 리턴한다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val players = Players(listOf(givenGamer1, givenGamer2))

        val updatedPlayers = players.endPlayerTurn(givenGamer1)
        val actual = updatedPlayers.players.find { it == givenGamer1 }

        assertThat(actual?.isBurst()).isFalse
    }

    @Test
    fun `카드를 받으면 카드 받은 상태를 리턴한다`() {
        val givenName1 = Name("player1")
        val givenName2 = Name("player2")
        val givenGamer1 = Gamer(givenName1)
        val givenGamer2 = Gamer(givenName2)
        val players = Players(listOf(givenGamer1, givenGamer2))
        val givenDeck = Deck()

        val updatedPlayers = players.receiveCards(givenGamer1, givenDeck)
        val actual = updatedPlayers.players.find { it.getPlayerName() == givenName1 }

        assertThat(actual?.openCards()).hasSize(1)
    }
}
