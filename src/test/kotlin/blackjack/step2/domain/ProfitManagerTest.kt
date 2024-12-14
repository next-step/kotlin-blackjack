package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProfitManagerTest : FunSpec({

    fun createCards(vararg numbers: CardNumber): Cards {
        // 간단히 동일한 카드 타입을 사용
        val cardType = CardType.SPADE
        return Cards.of(numbers.map { Card(it, cardType) })
    }

    test("딜러와 플레이어 모두 블랙잭이면 플레이어는 배팅금액을 돌려받는다. (수익은 0원)") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.ACE, CardNumber.KING))
        val player = Player("dongyeon", createCards(CardNumber.ACE, CardNumber.KING))
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe 0.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe 0.0
    }

    test("플레이어만 블랙잭이면 플레이어는 배팅금액의 1.5배를 가져간다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TEN, CardNumber.NINE)) // 19점
        val player = Player("dongyeon", createCards(CardNumber.ACE, CardNumber.KING))
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe 15000.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe -15000.0
    }

    test("플레이어 버스트이면 플레이어는 배팅금액 전액을 잃는다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TWO, CardNumber.NINE)) // 11점
        val player =
            Player("dongyeon", createCards(CardNumber.TEN, CardNumber.JACK, CardNumber.TWO)) // 22점
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe -10000.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe 10000.0
    }

    test("딜러 버스트이면 플레이어들은 배팅금액만큼 수익을 얻는다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TEN, CardNumber.JACK, CardNumber.TWO)) // 22점
        val player1 = Player("dongyeon", createCards(CardNumber.EIGHT, CardNumber.TEN)) // 18점
        val player2 = Player("pobi", createCards(CardNumber.KING, CardNumber.QUEEN)) // 20점
        val participants = listOf(dealer, player1, player2)
        val bets = listOf(Bet(player1.name, 10000), Bet(player2.name, 20000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val player1Result = results.first { it.participant == player1 }
        player1Result.profit shouldBe 10000.0

        val player2Result = results.first { it.participant == player2 }
        player2Result.profit shouldBe 20000.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe -(10000.0 + 20000.0)
    }

    test("플레이어 점수가 딜러보다 높으면 플레이어는 배팅금액만큼 얻는다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TWO, CardNumber.FOUR)) // 6점
        val player = Player("dongyeon", createCards(CardNumber.KING, CardNumber.FIVE)) // 15점
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe 10000.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe -10000.0
    }

    test("플레이어 점수가 딜러보다 낮으면 플레이어는 배팅금액만큼 잃는다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TEN, CardNumber.NINE)) // 19점
        val player = Player("dongyeon", createCards(CardNumber.TEN, CardNumber.EIGHT)) // 18점
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe -10000.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe 10000.0
    }

    test("플레이어와 딜러 점수가 같으면 수익은 0이다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TEN, CardNumber.KING)) // 20점
        val player = Player("dongyeon", createCards(CardNumber.KING, CardNumber.QUEEN)) // 20점
        val participants = listOf(dealer, player)
        val bets = listOf(Bet(player.name, 10000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val playerResult = results.first { it.participant == player }
        playerResult.profit shouldBe 0.0

        val dealerResult = results.first { it.participant == dealer }
        dealerResult.profit shouldBe 0.0
    }

    test("여러 플레이어가 있을 때 최종 딜러 수익은 모든 플레이어 수익의 합의 음수다.") {
        // given
        val dealer = Dealer(cards = createCards(CardNumber.TWO, CardNumber.THREE)) // 5점
        val player1 = Player("dongyeon", createCards(CardNumber.NINE, CardNumber.EIGHT)) // 17점
        val player2 =
            Player("pobi", createCards(CardNumber.TWO, CardNumber.TEN, CardNumber.JACK)) // 22점(bust)
        val player3 = Player("jason", createCards(CardNumber.ACE, CardNumber.KING)) // 블랙잭
        val participants = listOf(dealer, player1, player2, player3)
        val bets = listOf(Bet(player1.name, 10000), Bet(player2.name, 20000), Bet(player3.name, 30000))
        val betManager = BetManager(bets)

        // when
        val results = ProfitManager.calculateFinalProfit(participants, betManager)

        // then
        val result1 = results.first { it.participant == player1 } // 플레이어 점수가 딜러보다 높으므로 +10000
        val result2 = results.first { it.participant == player2 } // bust -> -20000
        val result3 = results.first { it.participant == player3 } // 블랙잭 -> +45000 (1.5배)
        val dealerResult = results.first { it.participant == dealer }

        result1.profit shouldBe 10000.0
        result2.profit shouldBe -20000.0
        result3.profit shouldBe 45000.0

        dealerResult.profit shouldBe -(result1.profit + result2.profit + result3.profit)
    }
})
