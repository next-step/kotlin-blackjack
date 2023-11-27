package blackjack.model.blackjack.judgment.impl

import blackjack.model.card.CardFixture
import blackjack.model.card.CardFixture.makeCards
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.player.playable.impl.Player
import blackjack.model.result.PlayableResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackJudgmentTest : StringSpec({

    "플레이어와 딜러 모두 burst가 아니라면 점수가 높은 쪽이 승리해야 한다" {
        val player = Player("pi", makeCards(CardFixture.two))
        val dealer = Dealer(makeCards(CardFixture.three))

        player.result(dealer) shouldBe PlayableResult.LOSE
        dealer.result(player) shouldBe PlayableResult.WIN
    }
    "플레이어와 딜러 모두 burst 라면 DRAW 이어야한다" {
        val player = Player("pi", makeCards(CardFixture.jack, CardFixture.queen, CardFixture.five))
        val dealer = Dealer(makeCards(CardFixture.three, CardFixture.ten, CardFixture.nine))

        player.result(dealer) shouldBe PlayableResult.DRAW
        dealer.result(player) shouldBe PlayableResult.DRAW
    }

    "플레이어가 burst 이고, 딜러는 burst 가 아니라면 딜러가 승리해야 한다" {
        val player = Player("pi", makeCards(CardFixture.jack, CardFixture.queen, CardFixture.five))
        val dealer = Dealer(makeCards(CardFixture.three, CardFixture.ten, CardFixture.four))

        player.result(dealer) shouldBe PlayableResult.LOSE
        dealer.result(player) shouldBe PlayableResult.WIN
    }

    "딜러가 burst 이고, 플레이어는 burst 가 아니라면 플레이어가 승리해야 한다" {
        val player = Player("pi", makeCards(CardFixture.jack, CardFixture.two, CardFixture.five))
        val dealer = Dealer(makeCards(CardFixture.three, CardFixture.ten, CardFixture.nine))

        player.result(dealer) shouldBe PlayableResult.WIN
        dealer.result(player) shouldBe PlayableResult.LOSE
    }

    "플레이어와 딜러 모두 BlackJack(21점) 이라면 DRAW 이어야한다" {
        val player = Player("pi", makeCards(CardFixture.jack, CardFixture.queen, CardFixture.ace2))
        val dealer = Dealer(makeCards(CardFixture.three, CardFixture.ten, CardFixture.eight))

        player.result(dealer) shouldBe PlayableResult.DRAW
        dealer.result(player) shouldBe PlayableResult.DRAW
    }
})
