package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class CardDeckTest : FunSpec({
    context("카드를 생성한다") {
        val cardNumbers = CardNumber.values().toList()
        val cardSymbols = CardSymbol.values().toList()

        val result = CardDeck()

        withData(cardNumbers) { cardNumber ->
            withData(cardSymbols) { cardSymbol ->
                result.cards shouldContain Card(cardNumber, cardSymbol)
            }
        }
    }

    context("카드를 뽑으면 카드한장이 사라진다") {
        // given
        val cardDeck = CardDeck()
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
        }.shouldHaveMessage("드로우 갯수에 비해 카드 수가 부족합니다.")
    }
})
