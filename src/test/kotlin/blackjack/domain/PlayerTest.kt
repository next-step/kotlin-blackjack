package blackjack.domain

import blackjack.fixture.ACE_SPADE
import blackjack.fixture.BlackJackFixture
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PlayerTest: BehaviorSpec({
    given("BURST 상태의 플레이어가") {
        val player = BlackJackFixture.BURST_PLAYER
        `when`("hit를 시도 하면") {
            then("에러가 발생 한다.") {
                shouldThrow<RuntimeException> { player.hit(ACE_SPADE) }
            }
        }
        `when`("플레이어의 상태는") {
            then("버스트이다.") {
                player.status shouldBe PlayerStatus.BUST
            }
        }
    }
    given("BURST가 아닌 상태의 플레이어가") {
        val player = BlackJackFixture.NOT_BURST_PLAYER_17
        `when`("hit를 시도 하면") {
            then("정상적으로 카드를 소지 한다.") {
                shouldNotThrow<RuntimeException> { player.hit(ACE_SPADE) }
            }
        }
        `when`("플레이어의 상태는") {
            then("버스트가 아니다.") {
                player.status shouldNotBe PlayerStatus.BUST

            }
        }
    }
})
