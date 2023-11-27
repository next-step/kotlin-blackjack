package blackjack.model.playable.impl

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.card.CardFixture
import blackjack.model.card.pack.impl.ShuffledPack
import blackjack.model.player.BlackjackScore
import blackjack.model.player.Players
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.player.playable.impl.Player
import blackjack.model.player.playblestrategy.impl.DealerStrategy
import blackjack.model.result.PlayableResult
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
        val dealer = Dealer( // 22점
            CardFixture.makeCards(CardFixture.five, CardFixture.seven, CardFixture.king)
        )
        val player1 = Player(
            "seoul", // 13점
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king, CardFixture.two), status = BlackJackStatus.ALIVE
        )
        val player2 = Player(
            "wonju", // 21점
            CardFixture.makeCards(CardFixture.queen, CardFixture.king, CardFixture.ace2), status = BlackJackStatus.ALIVE
        )
        val actualDealerResult = dealer.dealerResult(
            Players(player1, player2)
        )

        actualDealerResult.winningCount shouldBe 0
        actualDealerResult.drawingCount shouldBe 0
        actualDealerResult.losingCount shouldBe 2
        dealer.result(player1) shouldBe PlayableResult.LOSE
        dealer.result(player2) shouldBe PlayableResult.LOSE
    }

    "딜러와 플레이어 모두가 Burst 상황이라면 경기 결과는 DRAW 이어야한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.three))
        val player1 = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.four), status = BlackJackStatus.ALIVE
        )
        val player2 = Player(
            "martini",
            CardFixture.makeCards(CardFixture.nine, CardFixture.eight, CardFixture.seven),
            status = BlackJackStatus.ALIVE
        )

        player1.result(dealer) shouldBe PlayableResult.DRAW
        player2.result(dealer) shouldBe PlayableResult.DRAW
        dealer.result(player1) shouldBe PlayableResult.DRAW
        dealer.result(player2) shouldBe PlayableResult.DRAW
    }
    "딜러는 플레이어보다 점수가 높은경우 WIN 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.ten)) // 20
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.five), // 15
            status = BlackJackStatus.ALIVE
        )

        dealer.result(player) shouldBe PlayableResult.WIN
        player.result(dealer) shouldBe PlayableResult.LOSE
    }

    "딜러는 플레이어보다 점수가 낮은경우 LOSE 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.three, CardFixture.four))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.seven, CardFixture.five), status = BlackJackStatus.ALIVE
        )

        dealer.result(player) shouldBe PlayableResult.LOSE
        player.result(dealer) shouldBe PlayableResult.WIN
    }

    "딜러는 플레이어와 점수가 같은 경우 DRAW 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.two))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.ace2), status = BlackJackStatus.ALIVE
        )

        dealer.score() shouldBe BlackjackScore(21)
        player.score() shouldBe BlackjackScore(21)
        player.result(dealer) shouldBe PlayableResult.DRAW
        dealer.result(player) shouldBe PlayableResult.DRAW
    }

    "딜러가 2승1패인 경우 경기결과가 잘 표현되어야한다" {
        val dealer = Dealer( // 15점
            CardFixture.makeCards(CardFixture.two, CardFixture.two, CardFixture.eight, CardFixture.three)
        )
        val player1 = Player(
            "saml", // 21점
            CardFixture.makeCards(CardFixture.five, CardFixture.king, CardFixture.six), status = BlackJackStatus.ALIVE
        )
        val player2 = Player(
            "ldap", // 18점
            CardFixture.makeCards(CardFixture.eight, CardFixture.queen, CardFixture.ace2), status = BlackJackStatus.ALIVE
        )
        val player3 = Player(
            "oauth", // 10점
            CardFixture.makeCards(CardFixture.two, CardFixture.eight), status = BlackJackStatus.ALIVE
        )
        val actual = dealer.dealerResult(
            Players(player1, player2, player3)
        )

        actual.winningCount shouldBe 1
        actual.drawingCount shouldBe 0
        actual.losingCount shouldBe 2
    }
})
