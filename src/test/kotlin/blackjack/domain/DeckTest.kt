package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 지정한 카드로 생성할 수 있다" {
        val expectedCard = Card(Suit.CLUB, Rank.ACE)

        val actual = Deck(listOf(expectedCard))

        actual.quantity shouldBe 1
    }

    "덱은 52장의 카드를 가진다" {
        val actual = Deck()

        actual.quantity shouldBe 52
    }

    "덱에 존재하는 카드를 뽑을 수 있다" {
        val expectedCard = Card(Suit.CLUB, Rank.ACE)
        val deck = Deck(listOf(expectedCard))

        val actual = deck.draw()

        actual shouldBe expectedCard
    }

    "카드를 한 장 뽑으면 덱의 크기가 1 줄어든다" {
        val actual = Deck(listOf(Card(Suit.CLUB, Rank.ACE)))
        val beforeSize = actual.quantity

        Deck()
        Deck(listOf(Card(Suit.CLUB, Rank.ACE)))

        actual.draw()

        actual.quantity shouldBe beforeSize - 1
    }

    "뽑을 카드가 없으면 에러가 발생한다" {
        val actual = Deck(emptyList())

        shouldThrow<IllegalStateException> {
            actual.draw()
        }
    }
})
