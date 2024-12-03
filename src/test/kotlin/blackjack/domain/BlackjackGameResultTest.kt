package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BlackjackGameResultTest : StringSpec({

    val playerA =
        Player.createNew(
            PlayerName("playerA"),
            listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.TWO, Suit.HEARTS)),
        )

    val playerB =
        Player.createNew(
            PlayerName("playerB"),
            listOf(Card(Rank.TEN, Suit.CLOVERS), Card(Rank.EIGHT, Suit.HEARTS)),
        )

    val playerC =
        Player.createNew(
            PlayerName("playerC"),
            listOf(Card(Rank.TEN, Suit.CLOVERS), Card(Rank.SEVEN, Suit.HEARTS)),
        )

    val playerD =
        Player.createNew(
            PlayerName("playerD"),
            listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.NINE, Suit.HEARTS)),
        )

    "딜러의 결과를 반환할 수 있다." {
        forAll(
            row(
                Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.HEARTS))),
                listOf(playerA, playerB, playerC, playerD),
                "딜러: 1승 1무 2패",
            ),
        ) { dealer, players, expected ->
            val blackjackGameResult = BlackjackGameResult(dealer, players)
            blackjackGameResult.extractDealerGameResult() shouldBe expected
        }
    }

    "플레이어의 결과를 반환할 수 있다." {
        forAll(
            row(
                Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.HEARTS))),
                listOf(playerA, playerB, playerC, playerD),
                linkedMapOf(
                    playerA to GameMatchResult.LOSE,
                    playerB to GameMatchResult.WIN,
                    playerC to GameMatchResult.DRAW,
                    playerD to GameMatchResult.WIN,
                ),
            ),
        ) { dealer, players, expected ->
            val blackjackGameResult = BlackjackGameResult(dealer, players)
            blackjackGameResult.extractPlayerGameResult() shouldBe expected
        }
    }
})
