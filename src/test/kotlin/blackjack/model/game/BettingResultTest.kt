package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSuit
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.state.playState.gameState.BlackJack
import blackjack.model.state.playState.gameState.Bust
import blackjack.model.state.playState.gameState.Stay
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BettingResultTest : DescribeSpec({

    fun 카드덱_20점(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.JACK, CardSuit.하트),
                Card(CardNumber.KING, CardSuit.다이아몬드)
            )
        )
    }

    fun 카드덱_블랙잭(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.하트),
                Card(CardNumber.KING, CardSuit.다이아몬드)
            )
        )
    }

    fun 카드덱_23점(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.SEVEN, CardSuit.하트),
                Card(CardNumber.EIGHT, CardSuit.하트),
                Card(CardNumber.EIGHT, CardSuit.다이아몬드)
            )
        )
    }

    describe("배팅 결과") {
        val playerBettingAmount = 10000.0

        context("플레이어만 블랙잭이면") {
            val dealer = Dealer(Bust(카드덱_23점()))
            val player = Player("jason", bettingAmount = playerBettingAmount, state = BlackJack(카드덱_블랙잭()))

            it("플레이어는 1.5배를 딜러에게 받는다") {
                val bettingResult = BettingResult.toResult(MatchResult.toResult(dealer, listOf(player)))

                bettingResult.playerBenefits[player] shouldBe playerBettingAmount * 1.5
            }
        }

        context("딜러, 플레이어가 블랙잭이면") {
            val dealer = Dealer(BlackJack(카드덱_블랙잭()))
            val player = Player("jason", bettingAmount = playerBettingAmount, state = BlackJack(카드덱_블랙잭()))

            it("플레이어는 배팅금액을 돌려받는다") {
                val bettingResult = BettingResult.toResult(MatchResult.toResult(dealer, listOf(player)))

                bettingResult.playerBenefits[player] shouldBe playerBettingAmount
            }
        }

        context("딜러가 승리하면") {
            val dealer = Dealer(Stay(카드덱_20점()))
            val player = Player("jason", bettingAmount = playerBettingAmount, state = Bust(카드덱_23점()))

            it("플레이어는 배팅금액을 잃는다") {
                val bettingResult = BettingResult.toResult(MatchResult.toResult(dealer, listOf(player)))

                bettingResult.playerBenefits[player] shouldBe -playerBettingAmount
            }
        }
    }
})
