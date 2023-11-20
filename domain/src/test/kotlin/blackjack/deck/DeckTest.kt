import blackjack.deck.Deck
import blackjack.deck.RandomCardShuffler
import blackjack.deck.StandardCardProvider
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
        val initialSize = deck.size
        deck.drawCard()
        deck.size shouldBe (initialSize - 1)
    }
})
