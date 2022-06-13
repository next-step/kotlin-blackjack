package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class CardDeckTest : FreeSpec({
    lateinit var takeCards: Cards
    "처음 생성된 덱은 총 52개의 카드를 가진다" {
        CardDeck.all().size shouldBe 52
    }

    "랜덤하게 카드를 한개 가져온다" {
        takeCards.add(CardDeck.hit())
        takeCards.size() shouldBe 3
    }

    "시작 시 랜덤하게 카드 두개를 가져온다" {
        takeCards = CardDeck.start()
        takeCards.size() shouldBe 2
    }

    "한번 가져온 카드는 덱에 존재하지 않는다" {
        CardDeck.all() shouldNotContain takeCards.cards[0]
    }

    "꺼내온 카드는 중복되지 않는다" {
        takeCards.cards.shouldBeUnique()
    }
})
