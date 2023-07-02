package blackjack

import blackjack.TestUtils.Companion.card
import blackjack.card.deck.BlackJackCardDeck
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSymbolSignature
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "초기에 플레이어에게 전달할 두 장의 카드를 반환한다" {
        val sut =
            Dealer(
                BlackJackCardDeck(
                    card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER),
                    card(CardOrdinalSignature.TEN, CardSymbolSignature.CLOVER),
                ),
            )
        val players = listOf(Player("pobi"), Player("jason"))
        val cards = sut.provideInitialCards()
        players.all { cards.size == 2 } shouldBe true
    }

    "초기에 플레이어에게 전달할 한 장의 카드를 반환한다" {
        val sut =
            Dealer(
                BlackJackCardDeck(
                    card(CardOrdinalSignature.TEN, CardSymbolSignature.CLOVER),
                ),
            )
        shouldNotThrowAny {
            sut.provideCard()
        }
    }
})
