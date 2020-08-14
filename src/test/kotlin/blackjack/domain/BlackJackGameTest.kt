package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.LinkedList
import java.util.Queue

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
    fun `딜러가 17점 이상이면 더이상 카드를 뽑지 않는지 확인`() {
        // given
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.denominationOf("7")
            }

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }
        val game = BlackJackGame(deck)
        val dealer: Player = Dealer(Cards.denominationsOf("5", "6"))

        // when
        val resultDealer = game.playDealer(dealer)

        // then
        assertAll(
            { assertThat(resultDealer.state).isEqualTo(State.Stand) },
            { assertThat(resultDealer.getScore()).isEqualTo(18) }
        )
    }

    @Test
    fun `플레이어가 원할 때 플레이를 멈출 수 있는지 확인`() {
        // given
        val deck = object : DrawStrategy {
            val cards: Queue<Card> = LinkedList<Card>(
                listOf(
                    Card.denominationOf("5"),
                    Card.denominationOf("6")
                )
            )

            override fun fetchCard(): Card {
                return cards.poll()
            }

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }
        val game = BlackJackGame(deck)
        val player: Player = Challenger("Malibin", Cards.denominationsOf("3", "4"))

        // when 카드가 4장이면 멈추기
        val resultPlayer = game.play(listOf(player)) { it.cards.values.size != 4 }[0]

        // then
        assertAll(
            { assertThat(resultPlayer.getScore()).isEqualTo(18) },
            { assertThat(resultPlayer.cards.values).hasSize(4) },
            { assertThat(resultPlayer.state).isEqualTo(State.Stand) }
        )
    }
}
