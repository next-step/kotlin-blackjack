package blackjack.domain

import blackjack.fixture.BlackJackFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.be
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("딜러는") {
        val dealer = Dealer.create()

        `when`("BURST 상태의 플레어가 딜링을 요청 하면") {
            val beforeCountOfCard = dealer.countOfRemainCards()
            dealer.dealing(BlackJackFixture.ofPlayer.BURST_PLAYER)
            then("카드를 나누어 주지 않는다.") {
                val afterCountOfCard = dealer.countOfRemainCards()
                afterCountOfCard shouldBe beforeCountOfCard
            }
        }
    }
})
