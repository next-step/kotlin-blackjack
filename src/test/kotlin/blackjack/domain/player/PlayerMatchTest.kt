package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerMatchTest {

    @ParameterizedTest
    @MethodSource("provideDealers")
    fun `플레이어가 버스트 상태인 경우 딜러의 상태와 무관하게 패배한다`(dealer: Dealer) {
        val player = Player("플레이어", Bust(Cards(listOf(Card(Suit.CLUB, Denomination.KING)))))
        val result = player.matchResult(dealer)
        assertThat(result).isEqualTo(MatchingResult.LOSE)
    }

    @ParameterizedTest
    @MethodSource("providePlayers")
    fun `플레이어가 버스트 상태가 아니며 딜러가 버스트 상태인 경우 플레이어는 승리한다`(player: Player) {
        val dealer = Dealer("딜러", Bust(Cards(listOf(Card(Suit.CLUB, Denomination.KING)))))
        val result = player.matchResult(dealer)
        assertThat(result).isEqualTo(MatchingResult.WIN)
    }

    @ParameterizedTest
    @MethodSource("providePlayersAndDealer")
    fun `플레이어와 딜러 모두 버스트 상태가 아닌 경우 상태와 관련 없이 점수가 높은 쪽이 승리한다`(player: Player, dealer: Dealer, expected: MatchingResult) {
        val result = player.matchResult(dealer)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("providePlayersAndDealerDraw")
    fun `플레이어와 딜러 모두 버스트 상태가 아닌 경우 점수가 같다면 무승부이다`(player: Player, dealer: Dealer) {
        val result = player.matchResult(dealer)
        assertThat(result).isEqualTo(MatchingResult.DRAW)
    }

    companion object {
        @JvmStatic
        private fun provideDealers(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Dealer(
                        "딜러1",
                        Hit(Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE))))
                    )
                ),
                Arguments.of(
                    Dealer(
                        "딜러2",
                        Bust(
                            Cards(
                                listOf(
                                    Card(Suit.HEART, Denomination.KING),
                                    Card(Suit.HEART, Denomination.NINE),
                                    Card(Suit.HEART, Denomination.EIGHT)
                                )
                            )
                        )
                    )
                )
            )
        }

        @JvmStatic
        private fun providePlayers(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Player(
                        "플레이어1",
                        Hit(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE)))
                        )
                    )
                ),
                Arguments.of(
                    Player(
                        "플레이어2",
                        Stay(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE)))
                        )
                    )
                ),
                Arguments.of(
                    Player(
                        "플레이어3",
                        Blackjack(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.ACE)))
                        )
                    )
                )
            )
        }

        @JvmStatic
        private fun providePlayersAndDealer(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Player(
                        "플레이어1",
                        Stay(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE)))
                        )
                    ),
                    Dealer(
                        "딜러1",
                        Hit(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.EIGHT)))
                        )
                    ),
                    MatchingResult.WIN
                ),
                Arguments.of(
                    Player(
                        "플레이어2",
                        Stay(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE)))
                        )
                    ),
                    Dealer(
                        "딜러2",
                        Blackjack(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.ACE)))
                        )
                    ),
                    MatchingResult.LOSE
                )
            )
        }

        @JvmStatic
        private fun providePlayersAndDealerDraw(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Player(
                        "플레이어1",
                        Stay(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.NINE)))
                        )
                    ),
                    Dealer(
                        "딜러1",
                        Hit(
                            Cards(listOf(Card(Suit.HEART, Denomination.ACE), Card(Suit.HEART, Denomination.EIGHT)))
                        )
                    ),
                    MatchingResult.DRAW
                ),
                Arguments.of(
                    Player(
                        "플레이어2",
                        Blackjack(
                            Cards(listOf(Card(Suit.HEART, Denomination.QUEEN), Card(Suit.HEART, Denomination.ACE)))
                        )
                    ),
                    Dealer(
                        "딜러2",
                        Blackjack(
                            Cards(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.ACE)))
                        )
                    ),
                    MatchingResult.LOSE
                )
            )
        }
    }
}
