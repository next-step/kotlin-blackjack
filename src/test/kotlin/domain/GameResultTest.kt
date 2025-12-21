package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import model.*

class GameResultTest :
    FreeSpec({

        "게임 생성" - {
            "딜러 점수가 21을 넘지 않는다" {
                val dealer = Dealer(Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))))
                val winner =
                    Player("player1", Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))))
                val buster =
                    Player(
                        "player2",
                        Cards(
                            mutableListOf(
                                Card(Suit.SPADE, Rank.THREE),
                                Card(Suit.DIAMOND, Rank.QUEEN),
                                Card(Suit.SPADE, Rank.QUEEN),
                            ),
                        ),
                    )
                val loser =
                    Player("player3", Cards(mutableListOf(Card(Suit.SPADE, Rank.THREE), Card(Suit.DIAMOND, Rank.TEN))))
                val drawer =
                    Player("player4", Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))))

                val gameResult = GameResult.of(dealer, Players(listOf(winner, buster, loser, drawer)))

                gameResult.winners.players shouldContainExactlyInAnyOrder listOf(winner)
                gameResult.losers.players shouldContainExactlyInAnyOrder listOf(loser, buster)
                gameResult.draws.players shouldContainExactlyInAnyOrder listOf(drawer)
            }

            "딜러 점수가 21을 넘는다" {
                val dealer =
                    Dealer(
                        Cards(
                            mutableListOf(
                                Card(Suit.SPADE, Rank.TEN),
                                Card(Suit.DIAMOND, Rank.TEN),
                                Card(Suit.SPADE, Rank.TWO),
                            ),
                        ),
                    )
                val buster =
                    Player(
                        "player2",
                        Cards(
                            mutableListOf(
                                Card(Suit.SPADE, Rank.THREE),
                                Card(Suit.DIAMOND, Rank.QUEEN),
                                Card(Suit.SPADE, Rank.QUEEN),
                            ),
                        ),
                    )
                val winner =
                    Player("player1", Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))))

                val gameResult = GameResult.of(dealer, Players(listOf(winner, buster)))

                gameResult.winners.players shouldContainExactlyInAnyOrder listOf(winner)
                gameResult.losers.players shouldContainExactlyInAnyOrder listOf(buster)
            }
        }
    })
