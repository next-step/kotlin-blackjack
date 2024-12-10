package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class BlackjackGameTest {

    @Test
    fun `카드를 뽑으면 플레이어는 카드를 얻어야 한다`() {
        // given
        val player = Player(name = "jay", bettingAmount = 1)
        val blackjackGame = BlackjackGame(Deck(), Dealer(), listOf(player))

        // when
        blackjackGame.draw(player)

        // then
        assertThat(player.cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `게임이 시작을 하면 플레이어는 카드를 2장 얻어야 한다`() {
        // given
        val blackjackGame = BlackjackGame(Deck(), Dealer(), listOf(Player(name = "jay", bettingAmount = 1)))

        // when
        blackjackGame.start()

        // then
        val players = blackjackGame.participants.filterIsInstance<Player>()
        assertThat(players[0].cards.cards.size).isEqualTo(2)
    }

    @ParameterizedTest
    @MethodSource("provideTestParam")
    fun `게임 결과 테스트`(param: BlackjackGameResultTestParam) {
        // given
        val blackjackGame = BlackjackGame(
            Deck(mutableListOf()),
            param.dealer,
            listOf(param.player),
        )

        // when
        val result = blackjackGame.getGameResult()

        // then
        assertThat(result.playersResult[0].resultType).isEqualTo(param.playerResultType)
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
