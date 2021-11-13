package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GamersTest {

    @Test
    fun `게이머 목록을 생성할 수 있다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val actual = Gamers(listOf(givenGamer1, givenGamer2))

        assertThat(actual).isNotNull
    }

    @Test
    fun `게이머 이름을 입력받아 게이머 목록을 생성할 수 있다`() {
        val givenNames = Names(setOf(Name("player1"), Name("player2")))

        assertThat(Gamers.createGamers(givenNames)).isNotNull
    }

    @Test
    fun `게이머 목록에 한 명만 존재하면 예외를 던진다`() {
        val givenGamer1 = Gamer(Name("player1"))

        assertThrows<IllegalArgumentException> { Gamers(listOf(givenGamer1)) }
    }
}
