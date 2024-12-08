package blackjack

import blackjack.CardTextFixtures.spadeFourCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.CardTextFixtures.spadeThreeCard
import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.blackjackCards2
import blackjack.InitialCardsTestFixtures.initial16Cards
import blackjack.InitialCardsTestFixtures.initial18Cards
import blackjack.InitialCardsTestFixtures.initial18Cards2
import blackjack.InitialCardsTestFixtures.initial20Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BankTest : StringSpec({
    "은행은 딜러의 베팅 금액을 받는다" {
        val dealer = Dealer(initial18Cards)

        val sut = Bank()

        sut.bet(dealer, 0.0)

        sut.balance(dealer) shouldBe 0
    }

    "은행은 딜러가 베팅할 때 베팅 금액을 생략하면 0.0으로 베팅한다" {
        val dealer = Dealer(initial18Cards)

        val sut = Bank()

        sut.bet(dealer)

        sut.balance(dealer) shouldBe 0
    }

    "은행은 플레이어의 베팅 금액을 받는다" {
        val player = Player("pobi", initial18Cards)

        val sut = Bank()

        sut.bet(player, 1.0)

        sut.balance(player) shouldBe 1
    }

    "플레이어의 베팅 금액은 1원 이상이어야 한다" {
        val player = Player("pobi", initial18Cards)

        val sut = Bank()

        shouldThrow<IllegalArgumentException> { sut.bet(player, 0.0) }
    }

    "은행은 각 참가자의 잔고를 확인할 수 있다" {
        val dealer = Dealer(initial18Cards)
        val player = Player("pobi", initial20Cards)

        val sut = Bank()

        sut.bet(dealer, 0.0)
        sut.bet(player, 1.0)

        sut.balance(dealer) shouldBe 0
        sut.balance(player) shouldBe 1
    }

    "은행에 베팅하지 않은 참가자의 잔고는 0원이다" {
        val player = Player("pobi", initial20Cards)

        val sut = Bank()

        sut.balance(player) shouldBe 0
    }

    "은행은 게임 결과로 딜러가 플레이어에게 점수로 패배했을 때 참가자들에게 정산해줄 수 있다" {
        val dealer = Dealer(initial18Cards)
        val player = Player("pobi", initial20Cards)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.WIN)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe -1000
        sut.balance(player) shouldBe 2000
    }

    "은행은 게임 결과로 딜러가 플레이어에게 점수로 승리했을 때 참가자들에게 정산해줄 수 있다" {
        val dealer = Dealer(initial20Cards)
        val player = Player("pobi", initial18Cards)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.LOSS)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe 1000
        sut.balance(player) shouldBe 0
    }

    "은행은 게임 결과로 플레이어가 버스트했을 때 참가자들한테 정산할 수 있다" {
        val dealer = Dealer(initial20Cards)
        val player = Player("y2gcoder", initial18Cards)
        player.receive(spadeFourCard)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.LOSS)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe 1000
        sut.balance(player) shouldBe 0
    }

    "은행은 게임 결과로 딜러만 버스트했을 때 참가자들한테 정산할 수 있다" {
        val dealer = Dealer(initial16Cards)
        dealer.receive(spadeSixCard)
        val player = Player("y2gcoder", initial20Cards)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.WIN)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe -1000
        sut.balance(player) shouldBe 2000
    }

    "은행은 플레이어 블랙잭 승리에 대해 참가자들에게 정산할 수 있다(플레이어 +1.5, 딜러 -1.5)" {
        val dealer = Dealer(initial16Cards)
        val player = Player("y2gcoder", blackjackCards)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.BLACKJACK)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe -1500
        sut.balance(player) shouldBe 2500
    }

    "은행은 딜러가 블랙잭 승리했을 때(플레이어 패배) 참가자들에게 정산할 수 있다" {
        val dealer = Dealer(blackjackCards)
        val player = Player("y2gcoder", initial18Cards)
        player.receive(spadeThreeCard)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.LOSS)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe 1000
        sut.balance(player) shouldBe 0
    }

    "은행은 PUSH(플레이어 핸드 == 딜러 핸드)일 때 참가자들에게 정산할 수 있다" {
        val dealer = Dealer(initial18Cards)
        val player = Player("y2gcoder", initial18Cards2)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.PUSH)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe 0
        sut.balance(player) shouldBe 1000
    }

    "은행은 PUSH(플레이어 블랙잭 == 딜러 블랙잭)일 때 참가자들에게 정산할 수 있다" {
        val dealer = Dealer(blackjackCards)
        val player = Player("y2gcoder", blackjackCards2)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.PUSH)

        sut.settleBet(gameResult)

        sut.balance(dealer) shouldBe 0
        sut.balance(player) shouldBe 1000
    }

    "은행은 각 참가자의 수익률을 도출할 수 있다" {
        val dealer = Dealer(initial18Cards)
        val player1 = Player("pobi", initial20Cards)
        val player2 = Player("jason", initial16Cards)

        val sut =
            Bank(
                listOf(
                    ParticipantAccount2(dealer, Balance(Money(0.0), Money(10000.0))),
                    ParticipantAccount2(player1, Balance(Money(10000.0), Money(20000.0))),
                    ParticipantAccount2(player2, Balance(Money(20000.0), Money(0.0))),
                ),
            )

        val results = sut.profits()

        results.find { it.participant == dealer }?.profit shouldBe 10000.0
        results.find { it.participant == player1 }?.profit shouldBe 10000.0
        results.find { it.participant == player2 }?.profit shouldBe -20000.0
    }

    "은행은 경기 결과들을 받아 정산할 수 있다" {
        val dealer = Dealer(initial18Cards)
        val player1 = Player("pobi", initial20Cards)
        val player2 = Player("jason", initial16Cards)

        val sut = Bank()
        sut.bet(dealer, 0.0)
        sut.bet(player1, 1000.0)
        sut.bet(player2, 2000.0)

        val gameResults =
            listOf(
                GameResult2(player1, Outcome.WIN),
                GameResult2(player2, Outcome.LOSS),
            )

        sut.settleBets(gameResults)

        sut.balance(dealer) shouldBe 1000.0
        sut.balance(player1) shouldBe 2000.0
        sut.balance(player2) shouldBe 0.0
    }

    "은행은 정산하고자 할 때 딜러가 베팅한 상태여야 한다" {
        val player = Player("pobi", initial20Cards)

        val sut = Bank()
        sut.bet(player, 1000.0)

        val gameResult = GameResult2(player, Outcome.WIN)

        shouldThrow<IllegalStateException> { sut.settleBet(gameResult) }
    }

    "은행은 정산하고자 할 때 정산 대상 플레이어가 베팅한 상태야야 한다" {
        val dealer = Dealer(initial18Cards)
        val player = Player("pobi", initial20Cards)

        val sut = Bank()
        sut.bet(dealer)

        val gameResult = GameResult2(player, Outcome.WIN)

        shouldThrow<IllegalStateException> { sut.settleBet(gameResult) }
    }
})
