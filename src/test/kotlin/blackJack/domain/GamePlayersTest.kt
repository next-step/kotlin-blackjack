package blackJack.domain

import blackJack.domain.card.Card
import blackJack.domain.card.Denomination
import blackJack.domain.card.Suit
import blackJack.domain.player.Dealer
import blackJack.domain.player.GamePlayers
import blackJack.domain.player.Player
import blackJack.domain.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamePlayersTest {

    @Test
    fun `게임 플레이어 중에는 3명의 게임 플레이어가 있으며, 플레이어를 가지고 오면 그 수는 3명이다`() {
        // given
        val gamePlayers =
            GamePlayers(
                Players(
                    listOf(
                        Player.of("flamme").apply {
                            this.receiveCard() {
                                Card(Suit.HEARTS, Denomination.THREE)
                            }
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.KING)
                            }
                        },
                        Player.of("rain").apply {
                            this.receiveCard() {
                                Card(Suit.HEARTS, Denomination.TWO)
                            }
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.EIGHT)
                            }
                        },
                        Player.of("chacha").apply {
                            this.receiveCard() {
                                Card(Suit.HEARTS, Denomination.ACE)
                            }
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.KING)
                            }
                        }
                    )
                ),
                Dealer()
            )

        // when
        val player = gamePlayers.getPlayers()

        // then
        assertThat(player.size).isEqualTo(3)
    }
}
