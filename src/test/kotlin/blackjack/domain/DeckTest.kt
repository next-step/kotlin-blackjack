package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DeckTest : StringSpec({

    "카드 한 벌을 생성한다" {
        val cards = listOf(
            Card(Suite.DIAMONDS, Denomination.TEN),
            Card(Suite.HEARTS, Denomination.FOUR),
        )

        val deck = Deck(cards)

        deck.count shouldBe cards.size
    }

    "카드를 뽑을 수 있다" {
        val cards = listOf(
            Card(Suite.DIAMONDS, Denomination.TEN),
            Card(Suite.HEARTS, Denomination.FOUR),
        )

        val deck = Deck(cards)

        val cardOne = deck.draw()
        cardOne shouldBe cards[0]
        val cardTwo = deck.draw()
        cardTwo shouldBe cards[1]
    }

    "남아있는 카드가 없는경우 뽑을 때 에러가 발생한다" {
        val cards = listOf(Card(Suite.CLUBS, Denomination.QUEEN))
        val deck = Deck(cards)
        deck.draw()

        shouldThrow<IllegalStateException> {
            deck.draw()
        }
    }
})
