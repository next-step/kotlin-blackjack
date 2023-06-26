package blackjack.domain

import blackjack.domain.card.CardDeck
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class CardDeckTest : FunSpec({
    context("카드가 없을 때 카드를 뽑으면 에러 발생") {
        // given
        val cardDeck = CardDeck(mutableListOf())

        // when & then
        shouldThrow<IllegalArgumentException> {
            cardDeck.draw()
        }.shouldHaveMessage("드로우 갯수에 비해 카드 수가 부족합니다.")
    }
})
