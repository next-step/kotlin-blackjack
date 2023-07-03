package blackjack.domain

import blackjack.fixture.PlayerFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class DealerTest : BehaviorSpec({
    given("딜러는") {
        val dealer = Dealer.of(PlayerName.from("딜러"), ShuffledCardDeck())
        `when`("BURST 상태의 플레어가 딜링을 요청 하면") {
            then("예외가 발생 한다.") {
                shouldThrow<RuntimeException> { dealer.dealing(PlayerFixture.bust()) }
            }
        }
    }
})
