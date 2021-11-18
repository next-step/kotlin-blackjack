package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamePlayersTest {

    @Test
    fun `게임 플레이어 중에는 3명의 게임 플레이어가 있으며, 플레이어를 가지고 오면 그 수는 3명이다`() {
        // given
        val gamePlayers =
            GamePlayers(
                listOf(
                    Player.of("flamme").apply {
                        this.receiveCard(Card(Suit.HEARTS, Denomination.THREE))
                        this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                    },
                    Player.of("rain").apply {
                        this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
                        this.receiveCard(Card(Suit.SPADES, Denomination.EIGHT))
                    },
                    Player.of("chacha").apply {
                        this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                        this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                    },
                    Dealer()
                )
            )

        // when
        val player = gamePlayers.getPlayers()

        // then
        assertThat(player.size).isEqualTo(3)
    }

    @Test
    fun `게임 플레이어 중에 딜러가 없다면 에러`() {
        // given
        val gamePlayers =
            GamePlayers(listOf(
                Player.of("flamme").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.THREE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                },
                Player.of("rain").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
                    this.receiveCard(Card(Suit.SPADES, Denomination.EIGHT))
                },
                Player.of("chacha").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                }
            ))

        // when
        val actual = runCatching {
            gamePlayers.getDealer()
        }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("딜러가 없습니다.")
    }

    @Test
    fun `게임 플레이어 중에 딜러가 있다면 딜러를 반환시켜준다`() {
        // given
        val gamePlayers =
            GamePlayers(
                listOf(
                    Player.of("flamme").apply {
                        this.receiveCard(Card(Suit.HEARTS, Denomination.THREE))
                        this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                    },
                    Player.of("rain").apply {
                        this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
                        this.receiveCard(Card(Suit.SPADES, Denomination.EIGHT))
                    },
                    Dealer()
                )
            )

        // when
        val dealer = gamePlayers.getDealer()

        // then
        assertThat(dealer is Dealer).isTrue
    }
}
