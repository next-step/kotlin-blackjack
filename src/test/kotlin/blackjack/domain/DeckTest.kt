package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DeckTest : StringSpec({
    "초기화 시 52장의 카드가 생성된다." {
        val deck = Deck()
        deck.cards.size shouldBe 52
    }

    "카드는 중복되지 않는다." {
        val deck = Deck()
        val uniqueCards = deck.cards.distinct()
        uniqueCards.size shouldBe 52
    }

    "초기화 시 카드가 랜덤으로 섞인다." {
        val deck1 = Deck()
        val deck2 = Deck()
        deck1.cards shouldNotBe deck2.cards
    }

    "카드를 정상적으로 뽑을 수 있다." {
        val deck = Deck()
        val drawnCards = deck.drawCards(5)
        drawnCards.size shouldBe 5
        deck.cards.size shouldBe 47
    }

    "0장 이하의 카드를 뽑으려고 하면 예외가 발생한다." {
        val deck = Deck()
        shouldThrow<IllegalArgumentException> {
            deck.drawCards(0)
        }.message shouldBe "1장 이상의 카드를 뽑아야 합니다."
    }
})
