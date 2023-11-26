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

class MatchResultTest : DescribeSpec({

    fun 카드덱_18점(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.JACK, CardSuit.하트),
                Card(CardNumber.EIGHT, CardSuit.다이아몬드)
            )
        )
    }

    fun 카드덱_19점(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.JACK, CardSuit.하트),
                Card(CardNumber.NINE, CardSuit.다이아몬드)
            )
        )
    }

    fun 카드덱_20점(): CardDeck {
        return CardDeck(
            mutableListOf(
                Card(CardNumber.JACK, CardSuit.하트),
                Card(CardNumber.KING, CardSuit.다이아몬드)
            )
        )
    }

    fun 카드덱_21점(): CardDeck {
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

    describe("딜러가 Bust이고") {
        val dealer = Dealer(Bust(카드덱_23점()))

        it("플레이어가 Stay이면 플레이어의 승이다") {
            val player = Player("jason", Stay(카드덱_20점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.LOSE
        }

        it("플레이어가 Blackjack이면 플레이어의 승이다") {
            val player = Player("jason", BlackJack(카드덱_21점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.LOSE
        }

        // 플레이어가 먼저 bust 상태이므로 딜러의 승으로 판단한다
        it("플레이어가 Bust이면 딜러의 승이다") {
            val player = Player("jason", Bust(카드덱_23점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.WIN
        }
    }

    describe("딜러가 Blackjack이고") {
        val dealer = Dealer(BlackJack(카드덱_21점()))

        it("플레이어가 Stay이면 딜러의 승이다") {
            val player = Player("jason", Stay(카드덱_20점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.WIN
        }

        it("플레이어가 Blackjack이면 무승부이다") {
            val player = Player("jason", BlackJack(카드덱_21점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.DRAW
        }

        it("플레이어가 Bust이면 딜러의 승이다") {
            val player = Player("jason", Bust(카드덱_23점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.WIN
        }
    }

    describe("딜러가 Stay고") {
        val dealer = Dealer(Stay(카드덱_19점()))

        context("플레이어가 Stay이고") {
            it("딜러보다 점수가 더 높으면 플레이어의 승이다") {
                val player = Player("jason", Stay(카드덱_20점()))

                val matchResult = MatchResult.toResult(dealer, listOf(player))

                matchResult.dealerResult[0] shouldBe Rank.LOSE
            }

            it("딜러와 점수가 같으면 무승부이다") {
                val player = Player("jason", Stay(카드덱_19점()))

                val matchResult = MatchResult.toResult(dealer, listOf(player))

                matchResult.dealerResult[0] shouldBe Rank.DRAW
            }

            it("딜러보다 점수가 더 낮으면 딜러의 승이다") {
                val player = Player("jason", Stay(카드덱_18점()))

                val matchResult = MatchResult.toResult(dealer, listOf(player))

                matchResult.dealerResult[0] shouldBe Rank.WIN
            }
        }

        it("플레이어가 Blackjack이면 플레이어의 승이다") {
            val player = Player("jason", BlackJack(카드덱_21점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.LOSE
        }

        it("플레이어가 Bust이면 딜러의 승이다") {
            val player = Player("jason", Bust(카드덱_23점()))

            val matchResult = MatchResult.toResult(dealer, listOf(player))

            matchResult.dealerResult[0] shouldBe Rank.WIN
        }
    }
})
