package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class GameResultTest : FreeSpec({

    "게임 생성" - {
        "딜러 점수가 21을 넘지 않는다" {
            val dealer = Dealer(Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))))
            val winner =
                Player("player1", 1000, Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))))
            val buster =
                Player(
                    "player2",
                    1000,
                    Cards(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.THREE),
                            Card(Suit.DIAMOND, Rank.QUEEN),
                            Card(Suit.SPADE, Rank.QUEEN),
                        ),
                    ),
                )
            val loser =
                Player(
                    "player3",
                    1000,
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.THREE), Card(Suit.DIAMOND, Rank.TEN))),
                )
            val drawer =
                Player("player4", 1000, Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))))

            val gameResult = GameResult.of(dealer, Players(listOf(winner, buster, loser, drawer)))

            gameResult.winner.players shouldContainExactlyInAnyOrder listOf(winner)
            gameResult.loser.players shouldContainExactlyInAnyOrder listOf(loser, buster)
            gameResult.drawer.players shouldContainExactlyInAnyOrder listOf(drawer)
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
                    1000,
                    Cards(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.THREE),
                            Card(Suit.DIAMOND, Rank.QUEEN),
                            Card(Suit.SPADE, Rank.QUEEN),
                        ),
                    ),
                )
            val winner =
                Player("player1", 1000, Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))))

            val gameResult = GameResult.of(dealer, Players(listOf(winner, buster)))

            gameResult.winner.players shouldContainExactlyInAnyOrder listOf(winner)
            gameResult.loser.players shouldContainExactlyInAnyOrder listOf(buster)
        }
    }
})
