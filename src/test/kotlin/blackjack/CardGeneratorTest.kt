package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeckImpl
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CardGeneratorTest : FreeSpec({

    "getOne" - {

        "랜덤한 카드 한 장이 나와야한다." {
            val cardDeckImpl = CardDeckImpl()

            cardDeckImpl.getOne().shouldBeInstanceOf<Card>()
        }

        "카드를 모두 뽑았을 때 중복되는 카드가 없어야한다." {
            var cardDeckImpl = CardDeckImpl()
            val cards = List(48) {
                cardDeckImpl.getOne()
            }

            cards.distinct().size shouldBe 48
        }
    }
})
