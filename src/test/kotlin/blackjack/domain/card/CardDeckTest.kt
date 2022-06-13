package blackjack.domain.card

import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import blackjack.util.CardDeckFake
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CardDeckTest : FreeSpec({

    "RandomCardDeck" - {

        "랜덤한 카드 한 장이 나와야한다." {
            val randomCardDeck = RandomCardDeck()

            randomCardDeck.getOne().shouldBeInstanceOf<Card>()
        }

        "카드를 모두 뽑았을 때 중복되는 카드가 없어야한다." {
            var cardDeckImpl = RandomCardDeck()
            val cards = List(48) {
                cardDeckImpl.getOne()
            }

            cards.distinct().size shouldBe 48
        }
    }

    "Fake" - {

        "입력한 카드 리스트의 첫번째 카드가 나와야한다." {
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3")
            )
            val cardDeckFake = CardDeckFake(cards)

            cardDeckFake.getOne() shouldBe Card(RandomCardDeck.DIAMOND, "2")
        }
    }
})
