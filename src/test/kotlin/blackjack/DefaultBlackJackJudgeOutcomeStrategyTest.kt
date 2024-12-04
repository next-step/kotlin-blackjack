package blackjack

import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial16Cards
import blackjack.InitialCardsTestFixtures.initial18Cards
import blackjack.InitialCardsTestFixtures.initial19Cards
import blackjack.Suit.SPADES
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DefaultBlackJackJudgeOutcomeStrategyTest : StringSpec({
    "결과 판단 전략은 딜러보다 플레이어의 손패 합이 높을 때 각 플레이어들이 딜러에게 이겼다고 판단한다" {
        val dealer = Dealer(initial18Cards)

        val player = Player("jason", initial19Cards)

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.WIN
    }

    "결과 판단 전략은 딜러보다 플레이어 손패 합이 낮을 때 각 플레이들이 딜러에게 졌다고 판단한다" {
        val dealer = Dealer(initial19Cards)

        val player = Player("y2gcoder", initial18Cards)

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.LOSS
    }

    "결과 판단 전략은 딜러와 플레이어의 손패 합이 같으면 각 플레이어들이 딜러와 비겼다고 판단한다" {
        val dealer = Dealer(initial19Cards)

        val player = Player("y2gcoder", initial18Cards)
        player.receive(Card(CardNumber.Ace, SPADES))

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.DRAW
    }

    "결과 판단 전략은 플레이어가 파산했을 때는 해당 플레이어는 패배하고 딜러가 승리한 것으로 한다" {
        val dealer = Dealer(initial19Cards)

        val player = Player("y2gcoder", initial18Cards)
        player.receive(Card(Number(4), SPADES))

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.LOSS
    }

    "결과 판단 전략은 딜러와 플레이어 모두 파산하면 딜러의 승리로 본다" {
        val dealer = Dealer(initial16Cards)
        dealer.receive(Card(Number(6), SPADES))

        val player = Player("y2gcoder", initial18Cards)
        player.receive(Card(Number(4), SPADES))

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.LOSS
    }

    "결과 판단 전략은 딜러가 파산했을 때 플레이어가 파산하지 않았다면 플레이어의 승리로 본다" {
        val dealer = Dealer(initial16Cards)
        dealer.receive(Card(Number(6), SPADES))

        val player = Player("y2gcoder", initial18Cards)

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.WIN
    }

    "결과 판단 전략은 딜러만 블랙잭일 때 플레이어의 손패 합과 상관없이 플레이어의 패배로 판단한다" {
        val dealer = Dealer(blackjackCards)

        val player = Player("y2gcoder", initial16Cards)
        player.receive(Card(Number(5), SPADES))

        val sut = DefaultBlackJackJudgeOutcomeStrategy()

        val result = sut.judgeOutcome(dealer, player)

        result shouldBe Outcome.LOSS
    }
})
