package blackjack.domain

import blackjack.domain.player.Player
import blackjack.fixture.DIAMOND_KING
import blackjack.fixture.DealerFixture
import blackjack.fixture.HEART_EIGHT
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.HEART_SIX
import blackjack.fixture.PlayerFixture
import blackjack.fixture.SPADE_ACE
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultManagerTest : BehaviorSpec({
    given("딜러가 17점일 때") {

        val dealer: Dealer = DealerFixture.of(HEART_KING, HEART_SEVEN)

        `when`("플레이어가 블랙잭이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, SPADE_ACE)

            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액의 1.5배를 받는다.") { playerResult[0].profit shouldBe 150 }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_EIGHT)
            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 받는다.") { playerResult[0].profit shouldBe 100 }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SEVEN)
            val ( playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 잃는다.") { playerResult[0].profit shouldBe -100 }
        }

        `when`("플레이어가 16점이면 ") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SIX)

            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 잃는다.") { playerResult[0].profit shouldBe -100 }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val ( playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 잃는다.") { playerResult[0].profit shouldBe -100 }
        }
    }

    given("딜러가 버스트일 때") {
        val dealer: Dealer = DealerFixture.of(HEART_KING, DIAMOND_KING, HEART_SEVEN)

        `when`("플레이어가 블랙잭이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, SPADE_ACE)

            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액의 1.5배를 받는다.") { playerResult[0].profit shouldBe 150 }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_EIGHT)
            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 받는다.") { playerResult[0].profit shouldBe 100 }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SEVEN)
            val ( playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 받는다.") { playerResult[0].profit shouldBe 100 }
        }

        `when`("플레이어가 16점이면 ") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SIX)

            val (playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 받는다.") { playerResult[0].profit shouldBe 100 }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val ( playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어는 배팅한 금액을 잃는다.") { playerResult[0].profit shouldBe -100 }
        }
    }
})
