package blackjack.card

import blackjack.TestUtils.Companion.card
import blackjack.card.deck.PlayerCardDeck
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSymbolSignature
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

internal class PlayerCardDeckTest : StringSpec({
    "카드를 추가한다" {
        val sut = PlayerCardDeck()
        sut.addCard(card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER))
        sut.getCards() shouldHaveSize 1
    }
})
