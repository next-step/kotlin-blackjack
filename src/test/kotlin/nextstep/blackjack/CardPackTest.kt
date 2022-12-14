package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain

class CardPackTest : StringSpec({

    "카드팩은 플레이어에서 카드를 지급할 수 있다." {
        val card: Card = CardPack().draw()

        Card.values() shouldContain card
    }

    "카드팩은 한 게임에서 플레이어 상관없이 동일한 카드를 지급하지 않는다." {
        val cardPack = CardPack()
        val card: Card = cardPack.draw()

        cardPack.cardPack shouldNotContain card
    }
})
