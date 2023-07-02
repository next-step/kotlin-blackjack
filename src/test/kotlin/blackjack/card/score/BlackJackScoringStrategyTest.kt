package blackjack.card.score

import blackjack.TestUtils.Companion.card
import blackjack.card.deck.PlayerCardDeck
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSymbolSignature
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class BlackJackScoringStrategyTest : StringSpec({
    val strategy = BlackJackScoringStrategy()

    "주어진 카드로 블랙잭 룰로 점수를 계산한다" {
        val cards =
            listOf(
                card(CardOrdinalSignature.TWO, CardSymbolSignature.CLOVER),
                card(CardOrdinalSignature.TEN, CardSymbolSignature.HEART),
            )
        strategy.score(PlayerCardDeck(cards)) shouldBe 12
    }

    "21에 최대한 가까운 값이 되는 조합을 점수로 선택한다" {
        val cards =
            listOf(
                card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER),
                card(CardOrdinalSignature.TEN, CardSymbolSignature.HEART),
            )
        strategy.score(PlayerCardDeck(cards)) shouldBe 21
    }

    "A 는 21에 가까운 값으로 1, 11 중에 선택된다" {
        table(
            headers("cardList", "expectedScore"),
            row(
                listOf(
                    card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER),
                ),
                11,
            ),
            row(
                listOf(
                    card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER),
                    card(CardOrdinalSignature.K, CardSymbolSignature.CLOVER),
                ),
                21,
            ),
            row(
                listOf(
                    card(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER),
                    card(CardOrdinalSignature.TEN, CardSymbolSignature.CLOVER),
                    card(CardOrdinalSignature.FIVE, CardSymbolSignature.CLOVER),
                ),
                16,
            ),
        ).forAll { cardList, expectedScore ->
            strategy.score(PlayerCardDeck(cardList)) shouldBe expectedScore
        }
    }
})
