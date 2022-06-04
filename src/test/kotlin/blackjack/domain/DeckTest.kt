package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class DeckTest : StringSpec({
    val cards = listOf(
        Card(Suite.DIAMONDS, Denomination.TEN),
        Card(Suite.HEARTS, Denomination.FOUR),
    )

    "카드 한 벌을 생성한다" {
        val deck = Deck(cards)

        deck.count shouldBe cards.size
    }

    "카드를 뽑을 수 있다" {
        val deck = Deck(cards)

        val allCard = deck.drawAll()

        allCard shouldBe cards
    }

    "남아있는 카드가 없는경우 뽑을 때 에러가 발생한다" {
        val deck = Deck(cards)
        deck.drawAll()

        shouldThrow<IllegalStateException> {
            deck.draw()
        }
    }

    "트럼프 카드 한 벌을 생성한다" {
        val trump = Deck.shuffled().drawAll()
        val cardGroup = trump.groupBy { it.suite }

        cardGroup.size shouldBe 4
        cardGroup.values.forAll {
            it.distinctBy(Card::denomination).size shouldBe 13
        }
    }
})

fun Deck.drawAll(): List<Card> {
    val cards = mutableListOf<Card>()

    while (count > 0) {
        val card = draw()
        cards.add(card)
    }

    return cards
}
