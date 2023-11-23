package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSuit
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MatchResultTest : StringSpec({
    fun 딜러_20점(): Dealer {
        val dealer = Dealer()
        dealer.draw(Card(CardNumber.JACK, CardSuit.하트))
        dealer.draw(Card(CardNumber.KING, CardSuit.다이아몬드))

        return dealer
    }

    fun 딜러_21점(): Dealer {
        val dealer = Dealer()
        dealer.draw(Card(CardNumber.ACE, CardSuit.하트))
        dealer.draw(Card(CardNumber.KING, CardSuit.다이아몬드))

        return dealer
    }

    fun 딜러_23점(): Dealer {
        val dealer = Dealer()
        dealer.draw(Card(CardNumber.SEVEN, CardSuit.하트))
        dealer.draw(Card(CardNumber.EIGHT, CardSuit.하트))
        dealer.draw(Card(CardNumber.EIGHT, CardSuit.다이아몬드))

        return dealer
    }

    fun 플레이어_20점(): Player {
        val player = Player("jason")
        player.draw(Card(CardNumber.JACK, CardSuit.하트))
        player.draw(Card(CardNumber.KING, CardSuit.다이아몬드))

        return player
    }

    fun 플레이어_21점(): Player {
        val player = Player("jason")
        player.draw(Card(CardNumber.ACE, CardSuit.하트))
        player.draw(Card(CardNumber.KING, CardSuit.다이아몬드))

        return player
    }

    fun 플레이어_23점(): Player {
        val player = Player("jason")
        player.draw(Card(CardNumber.SEVEN, CardSuit.하트))
        player.draw(Card(CardNumber.EIGHT, CardSuit.하트))
        player.draw(Card(CardNumber.EIGHT, CardSuit.다이아몬드))

        return player
    }

    "딜러 21점 플레이어 20점이면 딜러가 승리한다" {
        // given
        val dealer = 딜러_21점()
        val player = 플레이어_20점()

        // when
        val matchResult = MatchResult.toResult(dealer, listOf(player))

        // then
        matchResult.dealerResult[0] shouldBe Rank.WIN
    }

    "딜러 23점 플레이어 23점이면 딜러가 승리한다" {
        // given
        val dealer = 딜러_23점()
        val player = 플레이어_23점()

        // when
        val matchResult = MatchResult.toResult(dealer, listOf(player))

        // then
        matchResult.dealerResult[0] shouldBe Rank.WIN
    }

    "딜러 20점 플레이어 20점이면 무승부이다" {
        // given
        val dealer = 딜러_20점()
        val player = 플레이어_20점()

        // when
        val matchResult = MatchResult.toResult(dealer, listOf(player))

        // then
        matchResult.dealerResult[0] shouldBe Rank.DRAW
    }

    "딜러 23점 플레이어 21점이면 플레이어가 승리한다" {
        // given
        val dealer = 딜러_23점()
        val player = 플레이어_21점()

        // when
        val matchResult = MatchResult.toResult(dealer, listOf(player))

        // then
        matchResult.dealerResult[0] shouldBe Rank.LOSE
    }

    "딜러 20점 플레이어 21점이면 플레이어가 승리한다" {
        // given
        val dealer = 딜러_20점()
        val player = 플레이어_21점()

        // when
        val matchResult = MatchResult.toResult(dealer, listOf(player))

        // then
        matchResult.dealerResult[0] shouldBe Rank.LOSE
    }
})
