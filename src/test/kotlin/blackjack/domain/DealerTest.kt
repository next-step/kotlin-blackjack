package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

internal class DealerTest : StringSpec({

    "딜러의 점수가 21점을 초과하면 버스트되지 않은 플레이어는 승리한다." {
        val dealer = Dealer(
            Card(Suite.SPADE, Denomination.SIX),
            Card(Suite.CLOVER, Denomination.JACK),
            Card(Suite.SPADE, Denomination.SEVEN)
        )
        val user = User(
            Card(Suite.CLOVER, Denomination.SEVEN),
            Card(Suite.SPADE, Denomination.TWO),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.WIN
    }

    "딜러와 플레이어 둘 다 버스트한 경우에는 딜러가 승리한다." {
        val dealer = Dealer(
            Card(Suite.SPADE, Denomination.SIX),
            Card(Suite.CLOVER, Denomination.JACK),
            Card(Suite.SPADE, Denomination.SEVEN)
        )
        val user = User(
            Card(Suite.CLOVER, Denomination.SEVEN),
            Card(Suite.SPADE, Denomination.EIGHT),
            Card(Suite.CLOVER, Denomination.KING)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.LOSE
    }

    "딜러의 점수가 21점이고 플레이어도 21점이라면 무승부다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.ACE)
        )

        val user = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.DRAW
    }

    "딜러의 점수보다 플레이어의 점수가 21에 더 가깝다면 플레이어가 승리한다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val user = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.NINE)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.WIN
    }

    "플레이어의 점수보다 딜러가 21에 더 가깝다면 플레이어는 패배한다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.NINE)
        )

        val user = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.SEVEN)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.LOSE
    }

    "두명의 플레이어와 대결할 때 딜려가 다 졌다면 2패의 결과를 가진다." {
        //  18점
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        // 21점
        val user1 = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        // 19점
        val user2 = User(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.NINE)
        )

        dealer.getMatchResult(user1)
        dealer.getMatchResult(user2)

        dealer.results shouldBe listOf(ResultStatus.LOSE, ResultStatus.LOSE)
    }

    "두 명의 플레이어와 대결 할 때 딜러가 1명의 플레이어만 이기고 1명은 졌다면 1승 1패의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val user1 = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val user2 = User(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.NINE)
        )

        dealer.getMatchResult(user1)
        dealer.getMatchResult(user2)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.LOSE)
    }

    "두 명의 플레이어와 대결 할 때 딜러가 모두 승리했다면 2승의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val user1 = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val user2 = User(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.SIX)
        )

        dealer.getMatchResult(user1)
        dealer.getMatchResult(user2)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.WIN)
    }

    "세 명의 플레이어와 대결 할 때 딜러가 두 명의 플레이어한테는 승리하고 한 명과는 무승부였다면 2승 1무의 결과를 가진다." {
        // 21 점
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.ACE)
        )

        // 15점
        val user1 = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        // 16점
        val user2 = User(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.SIX)
        )

        // 21점
        val user3 = User(
            Card(Suite.SPADE, Denomination.KING),
            Card(Suite.HEART, Denomination.ACE)
        )

        dealer.getMatchResult(user1)
        dealer.getMatchResult(user2)
        dealer.getMatchResult(user3)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.WIN, ResultStatus.DRAW)
    }

    "딜러의 점수가 16점이라면 카드를 더 뽑는다 (= true)" {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.FIVE),
            Card(Suite.DIAMOND, Denomination.JACK)
        )

        val result = dealer.isHit()

        result shouldBe true
    }

    "딜러의 점수가 17점 이라면 카드를 더 뽑을 수 없다. (= false)" {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.SEVEN),
            Card(Suite.DIAMOND, Denomination.JACK)
        )

        val result = dealer.isHit()

        result shouldBe false
    }

    "플레이어가 승리했다면 딜러는 패한다." {
        val dealer = Dealer()
        val playerResult = ResultStatus.WIN

        dealer.calculateResult(playerResult)

        dealer.results shouldContain ResultStatus.LOSE
    }

    "플레이어가 패했다면 딜러는 승리한다." {
        val dealer = Dealer()
        val playerResult = ResultStatus.LOSE

        dealer.calculateResult(playerResult)

        dealer.results shouldContain ResultStatus.WIN
    }

    "플레이어의 결과가 무승부라면 딜러도 무승부의 결과를 가진다." {
        val dealer = Dealer()
        val playerResult = ResultStatus.DRAW

        dealer.calculateResult(playerResult)

        dealer.results shouldContain ResultStatus.DRAW
    }

    "딜러가 블랙잭이면 블랙잭이 아닌 플레이어는 패한다." {
        val dealer = Dealer(
            Card(Suite.DIAMOND, Denomination.ACE),
            Card(Suite.SPADE, Denomination.KING)
        )

        val user = User(
            Card(Suite.SPADE, Denomination.EIGHT),
            Card(Suite.SPADE, Denomination.THREE),
            Card(Suite.CLOVER, Denomination.KING)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.LOSE
    }

    "딜러가 블랙잭이고 플레이어도 블랙잭이라면 무승부다." {
        val dealer = Dealer(
            Card(Suite.DIAMOND, Denomination.ACE),
            Card(Suite.SPADE, Denomination.KING)
        )

        val user = User(
            Card(Suite.SPADE, Denomination.ACE),
            Card(Suite.CLOVER, Denomination.KING)
        )

        val result = dealer.getMatchResult(user)

        result shouldBe ResultStatus.DRAW
    }

    /**
     * 딜러의 수익 계산 테스트
     */
    "딜러가 블랙잭이라면 블랙잭이 아닌 모든 플레이어의 베팅금액을 수거한다." {
        val blackJackUser = User(
            Card(Suite.HEART, Denomination.KING), Card(Suite.HEART, Denomination.ACE),
            betAmount = BetAmount(10000)
        )
        val loser1 = User(
            Card(Suite.SPADE, Denomination.EIGHT), Card(Suite.DIAMOND, Denomination.TWO),
            betAmount = BetAmount(10000)
        )
        val loser2 = User(
            Card(Suite.CLOVER, Denomination.FIVE), Card(Suite.DIAMOND, Denomination.QUEEN),
            betAmount = BetAmount(10000)
        )
        val blackJackDealer = Dealer(Card(Suite.SPADE, Denomination.ACE), Card(Suite.CLOVER, Denomination.QUEEN))
        val users = Users(listOf(blackJackUser, loser1, loser2))

        val userResults = users.calculateResult(blackJackDealer)
        val changedDealer = blackJackDealer.calculateProfit(userResults)
        val result = changedDealer.profit.value

        result shouldBe users.values.filter { !it.isBlackJack() }.sumOf { it.betAmount.value }
    }

    "플레이어가 블랙잭으로 우승했다면 플레이어 베팅금액의 2.5배를 플레이어에게 지급하고 딜러는 그만큼 수익에서 차감된다." {
        val blackJackUser = User(
            Card(Suite.HEART, Denomination.KING), Card(Suite.HEART, Denomination.ACE),
            betAmount = BetAmount(10000)
        )
        val dealer = Dealer(Card(Suite.SPADE, Denomination.ACE), Card(Suite.CLOVER, Denomination.FIVE))

        val userResultStatus = dealer.getMatchResult(blackJackUser)
        val userProfit = ProfitCalculator().calculate(blackJackUser, userResultStatus)
        val userResult = PlayerResult(blackJackUser, userResultStatus, userProfit)

        val changedDealer = dealer.calculateProfit(listOf(userResult))
        val result = changedDealer.profit.value

        result shouldBe -userProfit.value
    }

    "딜러가 2승 1패라면 승리한 플레이어들에게 베팅금액의 2배를 지급하고 패배한 플레이어의 베팅금액을 수거한다." {
        val loser1 = User(
            Card(Suite.HEART, Denomination.KING), Card(Suite.HEART, Denomination.FOUR),
            betAmount = BetAmount(10000)
        )
        val loser2 = User(
            Card(Suite.CLOVER, Denomination.KING), Card(Suite.HEART, Denomination.FIVE),
            betAmount = BetAmount(20000)
        )
        val winner = User(
            Card(Suite.HEART, Denomination.KING), Card(Suite.HEART, Denomination.NINE),
            betAmount = BetAmount(30000)
        )
        val dealer = Dealer(Card(Suite.SPADE, Denomination.EIGHT), Card(Suite.CLOVER, Denomination.QUEEN))
        val users = Users(listOf(loser1, loser2, winner))

        val userResults = users.calculateResult(dealer)
        val changedDealer = dealer.calculateProfit(userResults)
        val result = changedDealer.profit.value

        userResults.find { it.user == loser1}!!.profit.value shouldBe -loser1.betAmount.value
        userResults.find { it.user == loser2}!!.profit.value shouldBe -loser2.betAmount.value
        userResults.find { it.user == winner}!!.profit.value shouldBe winner.betAmount.value
        result shouldBe -userResults.sumOf { it.profit.value }
    }
})
