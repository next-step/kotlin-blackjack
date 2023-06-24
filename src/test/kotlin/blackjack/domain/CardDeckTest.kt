package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class CardDeckTest : FunSpec({
    context("카드를 생성한다") {
        val cardNumbers = CardNumber.values().toList()
        val cardSymbols = CardSymbol.values().toList()

        val result = CardDeck.create()

        withData(cardNumbers) { cardNumber ->
            withData(cardSymbols) { cardSymbol ->
                result.cards shouldContain Card(cardNumber, cardSymbol)
            }
        }
    }

    context("카드를 뽑으면 카드한장이 사라진다") {
        // given
        val cardDeck = CardDeck.create()
        // when
        cardDeck.draw()
        // then
        cardDeck.cards.size shouldBe 51
    }

    context("카드가 없을 때 카드를 뽑으면 에러 발생") {
        // given
        val cardDeck = CardDeck(mutableListOf())

        // when & then
        shouldThrow<IllegalArgumentException> {
            cardDeck.draw()
        }.shouldHaveMessage("카드 덱이 비어있습니다.")
    }

    context("초기 카드 뽑기는 카드가 2개 나와야한다") {
        val cardDeck =
            CardDeck(
                mutableListOf(Card(CardNumber.ACE, CardSymbol.CLUB), Card(CardNumber.TWO, CardSymbol.CLUB)),
                initDrawSize = 2
            )

        val result = cardDeck.initDraw()

        result.cards.size shouldBe 2
        result.cards shouldContainExactlyInAnyOrder listOf(
            Card(CardNumber.ACE, CardSymbol.CLUB),
            Card(CardNumber.TWO, CardSymbol.CLUB)
        )
    }
})
