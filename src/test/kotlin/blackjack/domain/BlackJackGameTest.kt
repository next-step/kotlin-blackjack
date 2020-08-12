package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackJackGameTest {

    @Test
    fun `플레이어들이 초기 두장씩 카드 갖고있는지 확인`() {
        // given
        val card = Card.denominationOf("A")
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return card
            }

            override fun getDealCards(): List<Card> {
                return listOf(card, card)
            }
        }
        val game = BlackJackGame(deck)
        val playerNames = listOf("Malibin")

        // when
        val players = game.dealWith(playerNames)

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

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }
        val game = BlackJackGame(deck)

        // when
        val player = game.askHit(Player("Malibin"), true)

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

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }
        val game = BlackJackGame(deck)

        // when
        val player = game.askHit(Player("Malibin"), false)

        // then
        assertAll(
            { assertThat(player.cards.values).hasSize(0) },
            { assertThat(player.state).isInstanceOf(State.Stand::class.java) }
        )
    }
}
