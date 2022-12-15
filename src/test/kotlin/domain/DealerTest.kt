package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class DealerTest : StringSpec({

    "딜러는 카드 뽑기 조건 만족시 카드를 뽑습니다"{
        val dealer = Dealer()

        dealer.takeCards(Card(CardNumber.TEN,CardShape.CLOVER))

        dealer.canDrawCard() shouldBe true

    }
    "딜러는 카드 뽑기 조건 불만족시 카드를 뽑지않습니다"{
        val dealer = Dealer()

        dealer.takeCards(Card(CardNumber.TEN,CardShape.CLOVER))
        dealer.takeCards(Card(CardNumber.EIGHT,CardShape.CLOVER))

        dealer.canDrawCard() shouldBe false
    }

})
