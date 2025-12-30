package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.maps.shouldContain

class GameResultTest : FreeSpec({

    "게임 생성" - {
        "딜러 점수가 21을 넘지 않는다" {
            val dealer =
                Dealer(Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.NINE))))
            val blackjackWinner =
                Player(
                    "player1",
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))),
                    10_000L,
                )
            val winner =
                Player(
                    "player2",
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))),
                    10_000L,
                )
            val buster =
                Player(
                    "player3",
                    Cards(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.THREE),
                            Card(Suit.DIAMOND, Rank.QUEEN),
                            Card(Suit.SPADE, Rank.QUEEN),
                        ),
                    ),
                    10_000L,
                )
            val loser =
                Player(
                    "player4",
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.THREE), Card(Suit.DIAMOND, Rank.TEN))),
                    10_000L,
                )
            val drawer =
                Player(
                    "player5",
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.NINE))),
                    10_000L,
                )

            val gameResult = GameResult.of(dealer, Players(listOf(blackjackWinner, winner, buster, loser, drawer)))

            gameResult.result.shouldContain("딜러", -5000L)
            gameResult.result.shouldContain("player1", 15_000L)
            gameResult.result.shouldContain("player2", 10_000L)
            gameResult.result.shouldContain("player3", -10_000L)
            gameResult.result.shouldContain("player4", -10_000L)
            gameResult.result.shouldContain("player5", 0L)
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
            val winner =
                Player(
                    "player1",
                    Cards(mutableListOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.DIAMOND, Rank.TEN))),
                    10_000L,
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
                    10_000L,
                )

            val gameResult = GameResult.of(dealer, Players(listOf(winner, buster)))

            gameResult.result.shouldContain("딜러", 0L)
            gameResult.result.shouldContain("player1", 10_000L)
            gameResult.result.shouldContain("player2", -10_000L)
        }
    }
})
