package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class GameResultJudgeTest {

    @ParameterizedTest
    @MethodSource("provideTestParam")
    fun `게임 결과를 테스트한다`(param: BlackjackGameResultTestParam) {
        // given
        val judge = GameResultJudge()

        // when
        val result = judge.judge(param.player.cards, param.dealer.cards)

        // then
        assertThat(result).isEqualTo(param.playerResultType)
    }

    companion object {
        @JvmStatic
        fun provideTestParam(): List<BlackjackGameResultTestParam> {
            // dealer cards sum 17
            val dealer = Dealer(
                name = "Dealer",
                cards = Cards(
                    mutableListOf(
                        Card(CardSuit.Heart, CardRank.Jack),
                        Card(CardSuit.Heart, CardRank.Seven),
                    )
                ),
            )
            return listOf(
                BlackjackGameResultTestParam(
                    dealer, Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Seven),
                            )
                        ), 1
                    ), GameResultType.PUSH
                ),
                BlackjackGameResultTestParam(
                    dealer, Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Eight),
                            )
                        ), 1
                    ), GameResultType.WIN
                ),
                BlackjackGameResultTestParam(
                    dealer, Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Six),
                            )
                        ), 1
                    ), GameResultType.LOSE
                ),
                BlackjackGameResultTestParam(
                    dealer, Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Ace),
                            )
                        ), 1
                    ), GameResultType.BLACKJACK_WIN
                ),
                BlackjackGameResultTestParam(
                    Dealer(
                        name = "Dealer",
                        cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Heart, CardRank.Jack),
                                Card(CardSuit.Heart, CardRank.Ace),
                            )
                        ),
                    ),
                    Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Ace),
                            )
                        ), 1
                    ),
                    GameResultType.PUSH,
                ),
                BlackjackGameResultTestParam(
                    Dealer(
                        name = "Dealer",
                        cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Heart, CardRank.Jack),
                                Card(CardSuit.Heart, CardRank.Queen),
                                Card(CardSuit.Heart, CardRank.Ten),
                            )
                        ),
                    ),
                    Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Ten),
                                Card(CardSuit.Spade, CardRank.Two),
                            )
                        ), 1
                    ),
                    GameResultType.WIN,
                ),
                BlackjackGameResultTestParam(
                    Dealer(
                        name = "Dealer",
                        cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Heart, CardRank.Jack),
                                Card(CardSuit.Heart, CardRank.Queen),
                                Card(CardSuit.Heart, CardRank.Ten),
                            )
                        ),
                    ),
                    Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Ten),
                            )
                        ), 1
                    ),
                    GameResultType.WIN,
                ),
                BlackjackGameResultTestParam(
                    Dealer(
                        name = "Dealer",
                        cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Heart, CardRank.Jack),
                                Card(CardSuit.Heart, CardRank.Queen),
                            )
                        ),
                    ),
                    Player(
                        "jay", cards = Cards(
                            mutableListOf(
                                Card(CardSuit.Spade, CardRank.Jack),
                                Card(CardSuit.Spade, CardRank.Ten),
                            )
                        ), 1
                    ),
                    GameResultType.PUSH,
                ),
            )
        }
    }

    data class BlackjackGameResultTestParam(
        val dealer: Dealer,
        val player: Player,
        val playerResultType: GameResultType,
    )

}
