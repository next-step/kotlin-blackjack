package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드를 생성한다." {
        val card = Card()
        card.cardList.size shouldBe 52
    }

    "카드를 한 장 뽑는다." {
        val card = Card()
        val drawnCards = card.drawCards(1)
        drawnCards.size shouldBe 1
        card.cardList.size shouldBe 51
    }

    "카드를 여러 장 뽑는다." {
        val card = Card()
        val drawnCards = card.drawCards(5)
        drawnCards.size shouldBe 5
        card.cardList.size shouldBe 47
    }

    "카드를 뽑을 때 남은 카드 수보다 많은 수를 요청하면 예외를 던진다." {
        val card = Card()
        shouldThrow<IllegalArgumentException> {
            card.drawCards(53)
        }
    }

    "카드를 뽑을 때 0 이하의 수를 요청하면 예외를 던진다." {
        val card = Card()
        shouldThrow<IllegalArgumentException> {
            card.drawCards(0)
        }
        shouldThrow<IllegalArgumentException> {
            card.drawCards(-1)
        }
    }
})
