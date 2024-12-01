package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "카드 뭉치는 가지고 있는 카드를 한장씩 뽑을 수 있다" {
        val cards = listOf(Card(CardNumber.ACE, Suit.SPADES), Card(CardNumber.TWO, Suit.SPADES))
        val sut = Deck(cards.toMutableList())

        val firstCard = sut.draw()
        val secondCard = sut.draw()

        firstCard shouldBe cards[0]
        secondCard shouldBe cards[1]
    }

    "카드 뭉치는 기본적으로 52장의 카드와 함께 만들어진다" {
        val sut = Deck()
        (1..52).map {
            sut.draw()
        }

        shouldThrow<NoSuchElementException> {
            sut.draw()
        }
    }

    "카드 뭉치에 있는 카드 수는 52장을 넘을 수 없다" {
        val cards =
            Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber ->
                    Card(cardNumber, suit)
                }
            }.toMutableList()
        cards.add(Card(CardNumber.KING, Suit.SPADES))

        shouldThrow<IllegalArgumentException> {
            Deck(cards = cards)
        }
    }

    "카드 뭉치에 현재 남아있는 카드 개수를 알 수 있다" {
        val sut = Deck()

        sut.size() shouldBe 52
        sut.draw()
        sut.size() shouldBe 51
    }

    "카드 뭉치를 셔플하면 카드 뭉치 내부의 카드 순서가 바뀐다" {
        val cards =
            mutableListOf(
                Card(CardNumber.QUEEN, Suit.SPADES),
                Card(CardNumber.KING, Suit.SPADES),
            )
        val notShuffledDeck = Deck(cards = cards)
        val notShuffledFirstCard = notShuffledDeck.draw()

        val shuffledDeck = Deck(cards = cards)
        shuffledDeck.shuffle(ReverseShuffler())
        val shuffledFirstCard = shuffledDeck.draw()

        notShuffledDeck.size() shouldBe 1
        shuffledDeck.size() shouldBe 1
        notShuffledFirstCard shouldBe Card(CardNumber.QUEEN, Suit.SPADES)
        shuffledFirstCard shouldBe Card(CardNumber.KING, Suit.SPADES)
    }
})

class ReverseShuffler : Shuffler {
    override fun shuffle(cards: MutableList<Card>) {
        cards.reverse()
    }
}
