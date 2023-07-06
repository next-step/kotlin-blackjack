package blackjack.domain.card

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardTest : BehaviorSpec({
    given("한 카드가 존재할 때") {
        val thisCard = Card(CardSuit.HEART, CardNumber.SEVEN)
        `when`("다른 카드와 모양과 숫자가 같으면") {
            val otherCard = Card(CardSuit.HEART, CardNumber.SEVEN)
            then("같은 카드 이다.") { thisCard shouldBe otherCard }
        }
        `when`("다른 카드와 모양은 같지만 숫자가 다르면") {
            val otherCard = Card(CardSuit.HEART, CardNumber.NINE)
            then("다른 카드 이다.") { thisCard shouldNotBe otherCard }
        }
        `when`("다른 카드와 숫자는 같지만 모양이 다르면") {
            val otherCard = Card(CardSuit.DIAMOND, CardNumber.SEVEN)
            then("다른 카드 이다.") { thisCard shouldNotBe otherCard }
        }
    }
})
