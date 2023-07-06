package blackjack.domain.player

import blackjack.domain.card.ShuffledCardDeck
import blackjack.fixture.DealerFixture
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.HEART_TWO
import blackjack.fixture.PlayerFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("딜러는") {
        val dealer = Dealer.of(PlayerName.from("딜러"), ShuffledCardDeck())
        `when`("버스트 상태의 플레어가 딜링을 요청 하면") {
            then("예외가 발생 한다.") {
                shouldThrow<RuntimeException> { dealer.dealing(PlayerFixture.bust()) }
            }
        }
    }

    given("딜러 카드 합이 16점 보다 작을 때") {
        val dealer = DealerFixture.of(HEART_KING, HEART_TWO)
        `when`("강제 히트 여부를 확인 하면") {
            then("true를 반환 한다.") {
                dealer.shouldHit() shouldBe true
            }
        }
    }

    given("딜러 카드 합이 16점 이상일 때") {
        val dealer = DealerFixture.of(HEART_KING, HEART_SEVEN)
        `when`("강제 히트 여부를 확인 하면") {
            then("false를 반환 한다.") {
                dealer.shouldHit() shouldBe false
            }
        }
    }
})
