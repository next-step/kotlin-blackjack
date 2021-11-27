package blackjack.domain.player

import blackjack.domain.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayersTest {

    @Test
    fun `플레이어 목록을 생성할 수 있다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenGamer1 = Gamer(profile1)

        val givenGamer2 = Gamer(profile2)
        val actual = Players.from(listOf(givenGamer1, givenGamer2, Dealer.of()))

        assertThat(actual).isNotNull
    }

    @Test
    fun `플레이어 이름을 입력받아 플레이어 목록을 생성할 수 있다`() {
        val givenNames = "player1, player2"

        assertThat(Players.from(givenNames)).isNotNull
    }

    @Test
    fun `플레이어 목록에 한 명만 존재하면 예외를 던진다`() {
        val givenGamer1 = Gamer(Profile.from(Name("player1")))

        assertThrows<IllegalArgumentException> { Players.from(listOf(givenGamer1)) }
    }

    @Test
    fun `플레이어의 첫 시작단계에서 카드를 받을 수 있다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenDeck = Deck()
        val givenGamer1 = Gamer(profile1)
        val givenGamer2 = Gamer(profile2)
        val players = Players.from(listOf(givenGamer1, givenGamer2, Dealer.of()))

        val actual = players.startInitPhase(givenDeck)

        assertThat(actual).allMatch { !it.openCards().isEmpty() }
    }

    @Test
    fun `플레이어가 모두 종료상태라면 true를 아니라면 false를 리턴한다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenGamer1 = Gamer(profile1)
        val givenGamer2 = Gamer(profile2)
        val players = Players.from(listOf(givenGamer1, givenGamer2, Dealer.of()))

        assertThat(players.isAllPlayerTurnOff()).isTrue
    }

    @Test
    fun `플레이어 목록을 준비로 설정하면 바뀌면 준비 상태를 리턴한다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenGamer1 = Gamer(profile1)
        val givenGamer2 = Gamer(profile2)
        val players = Players.from(listOf(givenGamer1, givenGamer2, Dealer.of()))

        val actual = players.turnToReady()

        assertThat(actual.players).allMatch { it.isBurst() }
    }

    @Test
    fun `플레이어의 턴을 종료하면 종료된 상태를 리턴한다`() {
        val profile1 = Profile.from(Name("player1"))
        val givenGamer1 = Gamer(profile1)
        val players = Players.from("player1, player2")

        val updatedPlayers = players.endPlayerTurn(givenGamer1)
        val actual = updatedPlayers.players.find { it.getPlayerName() == givenGamer1.getPlayerName() }

        assertThat(actual?.isBurst()).isFalse
    }

    @Test
    fun `플레이어 목록에 딜러가 2명이면 예외를 던진다`() {
        val dealer1 = Dealer.of()
        val dealer2 = Dealer.of()

        assertThrows<IllegalArgumentException> { Players.from(listOf(dealer1, dealer2)) }
    }
}
