package blackjack.model.playable.impl

import blackjack.model.Players
import blackjack.model.card.CardFixture
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.PlayableResult
import blackjack.model.playblestrategy.impl.DealerStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 처음에 받은 2장의 카드 점수 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.six)
        )
        dealer.playing(DealerStrategy(BlackjackScore(16)), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.six
        dealer.countOfCards() shouldBe 3
    }

    "딜러는 카드의 점수 합계가 17점 이상이면 추가로 받을 수 없다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.seven)
        )
        dealer.playing(DealerStrategy(BlackjackScore(17)), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.seven
        dealer.countOfCards() shouldBe 2
    }

    "딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해야 한다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.five, CardFixture.seven, CardFixture.king)
        )
        val player1 = Player(
            "seoul",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)
        )
        val player2 = Player(
            "wonju",
            CardFixture.makeCards(CardFixture.queen, CardFixture.king)
        )
        val actualDealerResult = dealer.dealerResult(
            Players(player1, player2)
        )

        actualDealerResult.score shouldBe BlackjackScore(22)
        actualDealerResult.winningCount shouldBe 0
        actualDealerResult.drawingCount shouldBe 0
        actualDealerResult.losingCount shouldBe 2
    }

    "딜러와 플레이어 모두가 Burst 상황이라면 플레이어 가 WIN(승리) 해야한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.three))
        val player1 = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.four)
        )
        val player2 = Player(
            "martini",
            CardFixture.makeCards(CardFixture.nine, CardFixture.eight, CardFixture.seven)
        )

        player1.result(dealer) shouldBe PlayableResult.WIN
        player2.result(dealer) shouldBe PlayableResult.WIN
        dealer.result(player1) shouldBe PlayableResult.LOSE
        dealer.result(player2) shouldBe PlayableResult.LOSE
    }
    "딜러는 플레이어보다 점수가 높은경우 WIN 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.ten)) // 20
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.five) // 15
        )

        dealer.result(player) shouldBe PlayableResult.WIN
        player.result(dealer) shouldBe PlayableResult.LOSE
    }

    "딜러는 플레이어보다 점수가 낮은경우 LOSE 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.three, CardFixture.four))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.seven, CardFixture.five)
        )

        dealer.result(player) shouldBe PlayableResult.LOSE
        player.result(dealer) shouldBe PlayableResult.WIN
    }

    "딜러는 플레이어와 점수가 같은 경우 DRAW 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.two))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.ace2)
        )

        dealer.score() shouldBe BlackjackScore(21)
        player.score() shouldBe BlackjackScore(21)
        player.result(dealer) shouldBe PlayableResult.DRAW
        dealer.result(player) shouldBe PlayableResult.DRAW
    }
})
