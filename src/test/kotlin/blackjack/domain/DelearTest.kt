package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DelearTest : BehaviorSpec({

    given("딜러 생성") {
        `when`("카드 점수를 16점으로 설정") {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.CARD_QUEEN, CardType.DIAMOND))
            dealer.addCard(Card(CardNumber.CARD_SIX, CardType.DIAMOND))
            then("카드를 한장 더 받아야 함") {
                dealer.shouldGetMoreCard() shouldBe true
            }
        }
        `when`("카드 점수를 17점으로 설정") {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.CARD_QUEEN, CardType.DIAMOND))
            dealer.addCard(Card(CardNumber.CARD_SEVEN, CardType.DIAMOND))
            then("카드를 한장 더 받지 않음") {
                dealer.shouldGetMoreCard() shouldBe false
            }
        }
    }
})
