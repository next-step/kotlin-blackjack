package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayingCardsTest : StringSpec({
    "플레잉 카드가 2Clover 3Spade일 때 카드 점수를 계산하면 5이다."{
        PlayingCards(mutableSetOf(Card.CLOVER_2, Card.SPADE_3)).sumPoint() shouldBe 5
    }

})
