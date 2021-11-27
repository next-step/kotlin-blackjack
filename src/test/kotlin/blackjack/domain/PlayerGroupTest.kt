package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerGroupTest {
    @Test
    fun `플레이어 그룹에는 딜러와 일반 플레이어들이 포함된다`() {
        val playerGroup = PlayerGroup(
            Player("player1"),
            Player("player2")
        )

        assertThat(playerGroup.dealer).isInstanceOf(Dealer::class.java)
        playerGroup.players.forEach {
            assertThat(it).isInstanceOf(Player::class.java)
        }
    }

    @Test
    fun `동일한 이름의 플레이어를 받으면 에러를 일으킨다`() {
        assertThrows<IllegalArgumentException> {
            PlayerGroup(
                Player("player1"),
                Player("player1")
            )
        }
    }

    @Test
    fun `최소 두 명 이상의 플레이어를 받지 않으면 에러를 일으킨다`() {
        assertThrows<IllegalArgumentException> {
            PlayerGroup(Player("player1"))
        }
    }

    @Test
    fun `게임에 승리한 플레이어를 리턴한다`() {
        val player1 = Player("player1")
        player1.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.KING)
        )

        val player2 = Player("player2")
        player2.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.NINE)
        )

        val playerGroup = PlayerGroup(player1, player2)

        assertThat(playerGroup.getWinners()).isEqualTo(listOf(player1))
    }

    @Test
    fun `동점으로 승리한 플레이어가 있는 경우 모두 리턴한다`() {
        val player1 = Player("player1")
        player1.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.KING)
        )

        val player2 = Player("player2")
        player2.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.QUEEN)
        )

        val playerGroup = PlayerGroup(player1, player2)

        assertThat(playerGroup.getWinners()).isEqualTo(listOf(player1, player2))
    }

    @Test
    fun `버스트된 플레이어는 승리한 플레이어에 포함되지 않는다`() {
        val player1 = Player("player1")
        player1.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.KING)
        )

        val player2 = Player("player2")
        player2.takeCards(
            Card(CardSymbol.CLOVER, CardNumber.QUEEN),
            Card(CardSymbol.CLOVER, CardNumber.JACK),
            Card(CardSymbol.CLOVER, CardNumber.TWO)
        )

        val playerGroup = PlayerGroup(player1, player2)

        assertThat(playerGroup.getWinners()).isEqualTo(listOf(player1))
    }
}
