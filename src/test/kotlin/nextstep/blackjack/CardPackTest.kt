package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain

class CardPackTest : StringSpec({

    "카드팩은 플레이어에서 카드를 지급할 수 있다." {
        val card: Card = CardPack().draw()

        Card.values() shouldContain card
    }

})
