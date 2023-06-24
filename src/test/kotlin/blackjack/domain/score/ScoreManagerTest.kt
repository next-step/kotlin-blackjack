package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.player.Player
import blackjack.domain.players
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class ScoreManagerTest : StringSpec({
    "점수가 버스트 플레이어" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TWO, CardSymbol.HEART),
            Card(CardNumber.JACK, CardSymbol.SPADE), Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        ScoreManager.isBustPlayer(player) shouldBe true
        ScoreManager.isNotBustPlayer(player) shouldBe false
    }
    "점수가 버스트 플레이어가 아닌 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        ScoreManager.isBustPlayer(player) shouldBe false
        ScoreManager.isNotBustPlayer(player) shouldBe true
    }
    "블랙잭인 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        ScoreManager.isBlackJack(player) shouldBe true
        ScoreManager.isNotBlackJack(player) shouldBe false
    }
    "블랙잭인 아닌 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.NINE, CardSymbol.SPADE),
            Card(CardNumber.TEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        ScoreManager.isBlackJack(player) shouldBe false
        ScoreManager.isNotBlackJack(player) shouldBe true
    }

    "GameResults 를 얻는다" {
        val cards1 = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.NINE, CardSymbol.SPADE),
            Card(CardNumber.TEN, CardSymbol.SPADE),
        )
        val player1 = Player("test", cards1)

        val cards2 = cards(
            Card(CardNumber.ACE, CardSymbol.HEART),
            Card(CardNumber.NINE, CardSymbol.HEART),
            Card(CardNumber.TEN, CardSymbol.HEART),
        )
        val player2 = Player("test", cards2)

        val players = players(player1, player2)
        val results = ScoreManager.getGameResults(players)

        results shouldContainExactlyInAnyOrder listOf(
            GameResult(player1, Score(20)),
            GameResult(player2, Score(20)),
        )
    }
})
