package blackJack.domain.result

import blackJack.domain.card.Card
import blackJack.domain.card.Denomination
import blackJack.domain.card.Suit
import blackJack.domain.player.Dealer
import blackJack.domain.player.GamePlayers
import blackJack.domain.player.Player
import blackJack.domain.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ResultsTest {
    @Test
    fun `딜러와 플레이어가 합쳐진 GamePlayers 를 넣고, 결과가 맞는지 확인한다`() {
        // given
        val gamePlayers =
            GamePlayers(
                Players(
                    listOf(
                        Player.of("flamme").apply {
                            this.receiveCard() {
                                Card(Suit.HEARTS, Denomination.ACE)
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
                                Card(Suit.HEARTS, Denomination.NINE)
                            }
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.KING)
                            }
                        }
                    )
                ),
                Dealer().apply {
                    this.receiveCard() {
                        Card(Suit.HEARTS, Denomination.NINE)
                    }
                    this.receiveCard() {
                        Card(Suit.SPADES, Denomination.KING)
                    }
                }

            )

        // when
        val results = Results.from(gamePlayers)
        val dealerResult = results.dealerResult

        // then
        assertAll({
            assertThat(dealerResult.win).isEqualTo(1)
            assertThat(dealerResult.lose).isEqualTo(1)
            assertThat(dealerResult.draw).isEqualTo(1)
        })
    }

    @Test
    fun `딜러가 버스터인 경우에 플레이어 한명은 버스터이고, 다른 두명은 스테이인 경우 플레이어 모두 승리한다`() {
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
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.EIGHT)
                            }
                            this.receiveCard() {
                                Card(Suit.SPADES, Denomination.SEVEN)
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
                Dealer().apply {
                    this.receiveCard() {
                        Card(Suit.HEARTS, Denomination.ACE)
                    }
                    this.receiveCard() {
                        Card(Suit.SPADES, Denomination.KING)
                    }
                    this.receiveCard() {
                        Card(Suit.SPADES, Denomination.EIGHT)
                    }
                    this.receiveCard() {
                        Card(Suit.SPADES, Denomination.SEVEN)
                    }
                }

            )

        // when
        val results = Results.from(gamePlayers)
        val dealerResult = results.dealerResult

        // then
        assertAll({
            assertThat(dealerResult.win).isEqualTo(0)
            assertThat(dealerResult.lose).isEqualTo(3)
            assertThat(dealerResult.draw).isEqualTo(0)
        })
    }
}
