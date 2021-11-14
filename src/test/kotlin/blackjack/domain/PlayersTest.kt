package blackjack.domain

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
}
