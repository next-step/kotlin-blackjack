package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackJackGameTest {

    @Test
    fun `플레이어들이 초기 두장씩 카드 갖고있는지 확인`() {
        // given
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.denominationOf("A")
            }
        }
        val game = BlackJackGame(deck)
        val players = listOf(Player("Malibin"))

        // when
        game.deal(players)

        // then
        assertThat(players[0].cards.values).hasSize(2)
    }

    @Test
    fun `플레이어가 hit했을 때 카드 수가 증가하는 지 확인`() {
        // given
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.denominationOf("A")
            }
        }
        val game = BlackJackGame(deck)
        val player = Player("Malibin")

        // when
        game.askHit(player, true)

        // then
        assertThat(player.cards.values).hasSize(1)
    }

    @Test
    fun `플레이어가 stand했을 때 상태 확인`() {
        // given
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.denominationOf("A")
            }
        }
        val game = BlackJackGame(deck)
        val player = Player("Malibin")

        // when
        game.askHit(player, false)

        // then
        assertAll(
            { assertThat(player.cards.values).hasSize(0) },
            { assertThat(player.state).isInstanceOf(State.Stand::class.java) }
        )
    }
}
