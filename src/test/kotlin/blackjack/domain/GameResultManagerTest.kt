package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.fixture.DIAMOND_KING
import blackjack.fixture.DealerFixture
import blackjack.fixture.HEART_EIGHT
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.HEART_SIX
import blackjack.fixture.PlayerFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultManagerTest : BehaviorSpec({
    given("딜러가 17점일 때") {

        val dealer: Dealer = DealerFixture.of(HEART_KING, HEART_SEVEN)

        `when`("플레이어가 16점이면") {
            val player: Player = PlayerFixture.of(HEART_KING, HEART_SIX)
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어 결과는 패 이다.") { playerResult[0].result shouldBe Result.LOSE }
            then("딜러 결과는 승 이다.") { dealerResult.win shouldBe 1 }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.of(HEART_KING, HEART_EIGHT)
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어 결과는 승 이다.") { playerResult[0].result shouldBe Result.WIN }
            then("딜러 결과는 패 이다.") { dealerResult.lose shouldBe 1 }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.of(HEART_KING, HEART_SEVEN)
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어 결과는 무 이다.") { playerResult[0].result shouldBe Result.DRAW }
            then("딜러 결과는 무 이다.") { dealerResult.draw shouldBe 1 }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))

            then("플레이어 결과는 패 이다.") { playerResult[0].result shouldBe Result.LOSE }
            then("딜러 결과는 승 이다.") { dealerResult.win shouldBe 1 }
        }
    }

    given("딜러가 버스트일 때") {
        val dealer: Dealer = DealerFixture.of(HEART_KING, DIAMOND_KING, HEART_SEVEN)

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.of(HEART_KING, HEART_SEVEN)
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))
            then("플레이어 결과는 승 이다.") { playerResult[0].result shouldBe Result.WIN }
            then("딜러 결과는 패 이다.") { dealerResult.lose shouldBe 1 }
        }
        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.of(HEART_KING, DIAMOND_KING, HEART_SEVEN)
            val (dealerResult, playerResult) = GameResultManager.calculate(dealer, listOf(player))
            then("플레이어 결과는 승 이다.") { playerResult[0].result shouldBe Result.WIN }
            then("딜러 결과는 패 이다.") { dealerResult.lose shouldBe 1 }
        }
    }
})
