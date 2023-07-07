package blackjack.domain.player

import blackjack.domain.Money
import blackjack.fixture.DIAMOND_KING
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.PlayerFixture
import blackjack.fixture.SPADE_ACE
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("버스트 상태의 플레이어가") {

        val player: Player = PlayerFixture.of(Money(100), HEART_KING, DIAMOND_KING, HEART_SEVEN)

        `when`("hit를 시도 하면") {
            then("에러가 발생 한다.") {
                shouldThrow<RuntimeException> { player.hit(SPADE_ACE) }
            }
        }

        `when`("플레이어의 상태는") {
            then("버스트이다.") {
                player.status shouldBe Status.BUST
            }
        }
    }

    given("버스트가 아닌 상태의 플레이어가") {

        `when`("hit를 시도 하면") {
            val player = PlayerFixture.of(Money(100), HEART_KING, HEART_SEVEN)
            then("정상적으로 카드를 소지 한다.") {
                shouldNotThrow<RuntimeException> { player.hit(SPADE_ACE) }
            }
        }

        `when`("스테이를 시도 하면") {
            val player = PlayerFixture.of(Money(100), HEART_KING, HEART_SEVEN)
            player.stay()
            then("스테이 상태 이다.") {
                player.status shouldBe Status.STAY
            }
        }

        `when`("카드의 합을 요청 하면") {
            val player = PlayerFixture.of(Money(100), HEART_KING, HEART_SEVEN)
            then("카드의 합이 반환 된다.") {
                player.total() shouldBe 17
            }
        }
    }
})
