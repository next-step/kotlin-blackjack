package blackjack.domain.gameresult

import blackjack.domain.Dealer
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

class RefereeTest : BehaviorSpec({
    given("딜러가 17점일 때 심판을 생성하고") {

        val dealer: Dealer = DealerFixture.of(HEART_KING, HEART_SEVEN)
        val referee = Referee(dealer)

        `when`("플레이어가 블랙잭이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, SPADE_ACE)
            val result = referee.referee(player)
            then("결과는 블랙잭(BLACKJACK)이다.") { result shouldBe Result.BLACKJACK }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_EIGHT)
            val result = referee.referee(player)

            then("결과는 승리(WIN)이다.") { result shouldBe Result.WIN }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SEVEN)
            val result = referee.referee(player)

            then("결과는 무승부(DRAW)이다.") { result shouldBe Result.DRAW }
        }

        `when`("플레이어가 16점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SIX)
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }
    }

    given("딜러가 버스트 일때 심판을 생성하고") {

        val dealer: Dealer = DealerFixture.of(HEART_KING, DIAMOND_KING, HEART_SEVEN)
        val referee = Referee(dealer)

        `when`("플레이어가 블랙잭이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, SPADE_ACE)
            val result = referee.referee(player)
            then("결과는 블랙잭(BLACKJACK)이다.") { result shouldBe Result.BLACKJACK }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_EIGHT)
            val result = referee.referee(player)

            then("결과는 승리(WIN)이다.") { result shouldBe Result.WIN }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SEVEN)
            val result = referee.referee(player)

            then("결과는 승리(WIN)이다.") { result shouldBe Result.WIN }
        }

        `when`("플레이어가 16점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SIX)
            val result = referee.referee(player)

            then("결과는 승리(WIN)이다.") { result shouldBe Result.WIN }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }
    }

    given("딜러가 블랙잭 일때 심판을 생성하고") {

        val dealer: Dealer = DealerFixture.of(HEART_KING, SPADE_ACE)
        val referee = Referee(dealer)

        `when`("플레이어가 블랙잭이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, SPADE_ACE)
            val result = referee.referee(player)
            then("결과는 블랙잭_무승부(BLACKJACK_DRAW)이다.") { result shouldBe Result.BLACKJACK_DRAW }
        }

        `when`("플레이어가 18점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_EIGHT)
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }

        `when`("플레이어가 17점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SEVEN)
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }

        `when`("플레이어가 16점이면") {
            val player: Player = PlayerFixture.bet100(HEART_KING, HEART_SIX)
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }

        `when`("플레이어가 버스트이면") {
            val player: Player = PlayerFixture.bust()
            val result = referee.referee(player)

            then("결과는 패배(LOSE)이다.") { result shouldBe Result.LOSE }
        }
    }
})
