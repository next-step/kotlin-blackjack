package blackjack.deck

import blackjack.card.CardFixture
import blackjack.card.Rank
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun draw() {
        val deck = generateTestDeck()
        val drawnCard = deck.draw()

        drawnCard shouldBe CardFixture.generateTestCard(Rank.ACE)
        deck.cards.cards shouldContainExactly
            listOf(
                CardFixture.generateTestCard(Rank.FIVE),
                CardFixture.generateTestCard(Rank.SIX),
            )
    }

    @Test
    fun `카드 목록이 비어있을때 뽑으면 에외를 던진다`() {
        val deck = Deck(cards = Cards(cards = listOf()))

        shouldThrow<IllegalArgumentException> {
            deck.draw()
        }
    }

    companion object {
        fun generateTestDeck() =
            Deck(
                Cards(
                    listOf(
                        CardFixture.generateTestCard(Rank.ACE),
                        CardFixture.generateTestCard(Rank.FIVE),
                        CardFixture.generateTestCard(Rank.SIX),
                    ),
                ),
            )
    }
}
