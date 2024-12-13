package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "초기화 시 52장의 카드가 생성된다." {
        val deck = Deck(shuffleStrategy = {})
        deck.getCurrentCardCount() shouldBe 52
    }

    "카드는 중복되지 않는다." {
        val deck = Deck(shuffleStrategy = {})
        val uniqueCards = deck.peekCards(52).distinct()
        uniqueCards.size shouldBe 52
    }

    "카드를 정상적으로 뽑을 수 있다." {
        val deck = Deck(shuffleStrategy = {})
        val firstCard = deck.peekCards(1).first()
        val drawnCards = deck.drawCards(5)

        drawnCards.size shouldBe 5
        drawnCards.first() shouldBe firstCard
        deck.getCurrentCardCount() shouldBe 47
    }

    "0장 이하의 카드를 뽑으려고 하면 예외가 발생한다." {
        val deck = Deck(shuffleStrategy = {})
        shouldThrow<IllegalArgumentException> {
            deck.drawCards(0)
        }.message shouldBe "1장 이상의 카드를 뽑아야 합니다."
    }
})
