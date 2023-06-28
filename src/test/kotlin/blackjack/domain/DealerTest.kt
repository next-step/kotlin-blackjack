package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("딜러는") {
        `when`("소지 하고 있는 카드가") {
            then("서로 다른 총 52장 이다.") {
                val dealer = Dealer.create()
                dealer.cards.size shouldBe 52
            }
        }
        `when`("소지 하고 있는 카드가 있을 때 딜링 하면") {
            then("소지 하고 있는 카드가 1장 줄어 든다.") {
                val dealer = Dealer.create()
                dealer.dealing()
                dealer.cards.size shouldBe 51
            }
        }
    }
})
