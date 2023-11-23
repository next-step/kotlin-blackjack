package step2.blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import step2.blackjack.domain.model.Deck

class DeckTest : StringSpec({
    "덱에 있는 카드를 모두 사용 했을 때 카드를 뽑을 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            val deck = Deck.all()
            repeat(53) {
                deck.peek()
            }
        }
    }
    "덱에 52장의 카드가 있는 경우 52번의 카드를 뽑을 수 있다." {
        val deck = Deck.all()
        repeat(52) {
            deck.peek()
        }
    }
})
