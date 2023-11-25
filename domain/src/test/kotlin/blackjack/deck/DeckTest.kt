import blackjack.deck.Deck
import blackjack.deck.FakeCardProvider
import blackjack.deck.RandomCardShuffler
import blackjack.deck.StandardCardProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "StandardCardProvider는 52장의 unique cards를 제공해야 한다." {
        val cardProvider = StandardCardProvider()
        val cards = cardProvider.provideCards()
        cards shouldHaveSize 52
    }

    "drawCard 함수가 실행되면 덱의 사이즈가 1 줄어든다." {
        val deck = Deck(StandardCardProvider(), RandomCardShuffler())
        val initialSize = deck.remainingCards
        deck.drawCard()
        deck.remainingCards shouldBe (initialSize - 1)
    }

    "덱이 비어있을 때 카드를 뽑으려고 하면 예외가 발생해야 한다" {
        val emptyDeck = Deck(cardProvider = FakeCardProvider())

        val exception = shouldThrow<IllegalStateException> {
            emptyDeck.drawCard()
        }
        exception.message shouldBe "덱에 카드가 없으면 카드를 뽑을 수 없습니다."
    }
})
