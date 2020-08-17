package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.card.DrawStrategy
import blackjack.domain.player.Challenger
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.LinkedList
import java.util.Queue

internal class BlackJackGameTest {

    @Test
    fun `플레이어들이 초기 두장씩 카드 갖고있는지 확인`() {
        // given
        val card = Card.spadeOf("A")
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return card
            }
        })
        val game = BlackJackGame(deck)
        val playerNames = listOf("Malibin")

        // when
        val players = game.dealWith(playerNames) { 0 }

        // then
        assertThat(players[0].cards.values).hasSize(2)
    }

    @Test
    fun `딜러가 17점 이상이면 더이상 카드를 뽑지 않는지 확인`() {
        // given
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.spadeOf("7")
            }
        })
        val game = BlackJackGame(deck)
        val dealer: Player = Dealer(cards = Cards("5", "6"))

        // when
        val resultDealer = game.playDealer(dealer)

        // then
        assertAll(
            { assertThat(resultDealer.canPlay()).isFalse() },
            { assertThat(resultDealer.getScore()).isEqualTo(18) }
        )
    }

    @Test
    fun `플레이어가 원할 때 플레이를 멈출 수 있는지 확인`() {
        // given
        val deck = Deck(object : DrawStrategy {
            val cards: Queue<Card> = LinkedList<Card>(
                listOf(
                    Card.spadeOf("5"),
                    Card.spadeOf("6")
                )
            )

            override fun fetchCard(): Card {
                return cards.poll()
            }
        })
        val game = BlackJackGame(deck)
        val player: Player = Challenger(PlayerInfo("Malibin", 0), Cards("3", "4"))

        // when 카드가 4장이면 멈추기
        val resultPlayer = game.play(
            listOf(player),
            isAgreedHit = { it.cards.values.size != 4 },
            printPlayerCards = {}
        )[0]

        // then
        assertAll(
            { assertThat(resultPlayer.getScore()).isEqualTo(18) },
            { assertThat(resultPlayer.cards.values).hasSize(4) },
            { assertThat(resultPlayer.canPlay()).isTrue() }
        )
    }
}
