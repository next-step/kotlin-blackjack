package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    val playerList = listOf(Player("jason", Cards(listOf(Card(CardNumber.Two, Suit.Heart)))))

    @Test
    fun `Player들을 속성으로 갖는다`() {
        assertThat(Players(playerList).values).isEqualTo(playerList)
    }

    @Test
    fun `아직 종료되지 않은 유저 목록을 반환한다`() {
        assertThat(Players(playerList).findNotOver()).isEqualTo(playerList)
        assertThat(Players(playerList.map { it.setStay() }).findNotOver()).isEmpty()
    }

    @Test
    fun `이름으로 플레이어를 찾는다`() {
        assertThat(Players(playerList).find("jason")).isNotNull
        assertThat(Players(playerList).find("jinwoo")).isNull()
    }
}
