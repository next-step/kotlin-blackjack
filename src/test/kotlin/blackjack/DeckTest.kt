package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Deck
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class DealerDeckDrawManyTestData(
    val cardList: List<Card>,
    val drawCount: Int,
)

class DeckTest : FunSpec({
    context("Deck에서 카드를 한 개 뽑는다.") {
        withData(
            listOf(
                listOf(Card(CardNumber.ACE, CardShape.CLOVER))
            )
        ) { deck ->
            val drawnCard: Card = Deck(deck).pop()

            drawnCard.number shouldBe deck.last().number
            drawnCard.shape shouldBe deck.last().shape
        }
    }

    context("Deck에서 카드를 여러 개 뽑는다.") {
        withData(
            listOf(
                DealerDeckDrawManyTestData(
                    cardList = listOf(
                        Card(CardNumber.ACE, CardShape.CLOVER),
                        Card(CardNumber.TEN, CardShape.HEART),
                        Card(CardNumber.KING, CardShape.HEART)
                    ),
                    drawCount = 2
                ),
            )
        ) { (cardList, drawCount) ->
            val deck = Deck(cardList)
            val drawnCards = deck.popMany(drawCount)

            drawnCards.count() shouldBe drawCount
            drawnCards.forEachIndexed { index, card ->
                card.number shouldBe cardList[cardList.count() - index - 1].number
                card.shape shouldBe cardList[cardList.count() - index - 1].shape
            }
        }
    }

    context("Deck보다 많은 수의 카드를 뽑으려고 하면 에러가 발생한다.") {
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
                Deck(deck).popMany(deck.count() + 1)
            }
        }
    }
})
