package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
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
        `when`("카드 점수를 21점으로 설정") {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.CARD_ACE, CardType.DIAMOND))
            dealer.addCard(Card(CardNumber.CARD_QUEEN, CardType.DIAMOND))
            then("딜러의 승패는 딜러 점수와 사용자 점수로 체크할 수 있음") {
                dealer.getScore() shouldBe 21
            }
        }
        `when`("카드 점수를 22점으로 설정") {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.CARD_QUEEN, CardType.DIAMOND))
            dealer.addCard(Card(CardNumber.CARD_KING, CardType.DIAMOND))
            dealer.addCard(Card(CardNumber.CARD_TWO, CardType.DIAMOND))
            then("딜러는 무조건 지는 조건이 됨") {
                dealer.getScore() shouldBe 0
            }
        }
    }
})
