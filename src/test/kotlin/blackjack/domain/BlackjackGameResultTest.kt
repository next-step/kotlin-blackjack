package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BlackjackGameResultTest : StringSpec({

    val playerScore12 =
        Player.createNew(
            PlayerName("playerA"),
            BettingMoney(10000),
            listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.TWO, Suit.HEARTS)),
        )

    val playerScore18 =
        Player.createNew(
            PlayerName("playerB"),
            BettingMoney(10000),
            listOf(Card(Rank.TEN, Suit.CLOVERS), Card(Rank.EIGHT, Suit.HEARTS)),
        )

    val playerScore17 =
        Player.createNew(
            PlayerName("playerC"),
            BettingMoney(10000),
            listOf(Card(Rank.TEN, Suit.CLOVERS), Card(Rank.SEVEN, Suit.HEARTS)),
        )

    val playerScoreBlackjack =
        Player.createNew(
            PlayerName("playerD"),
            BettingMoney(10000),
            listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.ACE, Suit.HEARTS)),
        )

    "플레이어의 결과를 반환할 수 있다." {
        forAll(
            row(
                Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.HEARTS))),
                listOf(playerScore12, playerScore18, playerScore17, playerScoreBlackjack),
                linkedMapOf(
                    playerScore12 to GameMatchResult.LOSE,
                    playerScore18 to GameMatchResult.WIN,
                    playerScore17 to GameMatchResult.DRAW,
                    playerScoreBlackjack to GameMatchResult.WIN,
                ),
            ),
        ) { dealer, players, expected ->
            val blackjackGameResult = BlackjackGameResult(dealer, players)
            blackjackGameResult.extractPlayerGameResult() shouldBe expected
        }
    }

    "딜러의 이익을 계산할 수 있다." {
        forAll(
            row(
                Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.HEARTS))),
                -15000,
            ),
            row(
                Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.ACE, Suit.HEARTS))),
                30000,
            ),
        ) { dealer, expected ->
            val players = listOf(playerScore12, playerScore18, playerScore17, playerScoreBlackjack)
            val blackjackGameResult = BlackjackGameResult(dealer, players)
            blackjackGameResult.calculateDealerProfit() shouldBe expected
        }
    }

    "플레이어 각각의 이익을 계산할 수 있다." {
        val dealer = Dealer.createNew(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.HEARTS)))
        val players = listOf(playerScore12, playerScore18, playerScore17, playerScoreBlackjack)

        val blackjackGameResult = BlackjackGameResult(dealer, players)
        val expectedProfits =
            mapOf(
                playerScore12 to -10000,
                playerScore18 to 10000,
                playerScore17 to 0,
                playerScoreBlackjack to 15000,
            )
        blackjackGameResult.calculatePlayerProfits() shouldBe expectedProfits
    }
})
