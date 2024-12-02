package blackjack.deck

import blackjack.card.CardFixture
import blackjack.card.Rank
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun draw() {
        val cards =
            Cards(
                cards =
                    listOf(
                        CardFixture.generateTestCard(Rank.ACE),
                        CardFixture.generateTestCard(Rank.FIVE),
                        CardFixture.generateTestCard(Rank.SIX),
                    ),
            )

        val (drawnCard, remainingCards) = cards.draw()

        drawnCard shouldBe CardFixture.generateTestCard(Rank.ACE)
        remainingCards shouldBe
            listOf(
                CardFixture.generateTestCard(Rank.FIVE),
                CardFixture.generateTestCard(Rank.SIX),
            )
    }

    @Test
    fun `카드 목록이 비어있을때 뽑으면 에외를 던진다`() {
        val cards = Cards(cards = listOf())

        shouldThrow<IllegalArgumentException> {
            cards.draw()
        }
    }
}
