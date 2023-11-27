package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class DealerDeckDrawManyTestData(
    val deck: List<Card>,
    val drawCount: Int,
)

class DealerTest : FunSpec({
    context("Dealer는 deck에서 카드를 한 개 뽑는다.") {
        withData(
            listOf(
                listOf(Card(CardNumber.ACE, CardShape.CLOVER))
            )
        ) { deck ->
            val drawnCard: Card = Dealer(deck).draw()

            drawnCard.number shouldBe deck.last().number
            drawnCard.shape shouldBe deck.last().shape
        }
    }

    context("Dealer는 deck에서 카드를 여러 개 뽑는다.") {
        withData(
            listOf(
                DealerDeckDrawManyTestData(
                    deck = listOf(
                        Card(CardNumber.ACE, CardShape.CLOVER),
                        Card(CardNumber.TEN, CardShape.HEART),
                        Card(CardNumber.KING, CardShape.HEART)
                    ),
                    drawCount = 2
                ),
            )
        ) { (deck, drawCount) ->
            val dealer = Dealer(deck)
            val drawnCards = dealer.drawMany(drawCount)

            drawnCards.count() shouldBe drawCount
            drawnCards.forEachIndexed { index, card ->
                card.number shouldBe deck[deck.count() - index - 1].number
                card.shape shouldBe deck[deck.count() - index - 1].shape
            }
        }
    }

    context("Dealer가 deck보다 많은 수의 카드를 뽑으려고 하면 에러가 발생한다.") {
        withData(
            listOf(
                listOf(
                    Card(CardNumber.ACE, CardShape.CLOVER),
                    Card(CardNumber.TEN, CardShape.HEART),
                ),
            )
        ) { deck ->
            shouldThrowWithMessage<IllegalArgumentException>(
                "Not enough cards are in deck!"
            ) {
                Dealer(deck).drawMany(deck.count() + 1)
            }
        }
    }
})
