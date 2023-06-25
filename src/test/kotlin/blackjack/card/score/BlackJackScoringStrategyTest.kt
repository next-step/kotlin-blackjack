package blackjack.card.score

import blackjack.card.Card
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSignaturePack
import blackjack.card.signature.CardSymbolSignature
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackScoringStrategyTest : StringSpec({
    val strategy = BlackJackScoringStrategy()

    "주어진 카드로 블랙잭 룰로 점수를 계산한다" {
        val cards =
            listOf(
                Card(CardSignaturePack(CardOrdinalSignature.TWO, CardSymbolSignature.CLOVER)),
                Card(CardSignaturePack(CardOrdinalSignature.TEN, CardSymbolSignature.HEART)),
            )
        strategy.score(cards) shouldBe 12
    }

    "21에 최대한 가까운 값이 되는 조합을 점수로 선택한다" {
        val cards =
            listOf(
                Card(CardSignaturePack(CardOrdinalSignature.ACE, CardSymbolSignature.CLOVER)),
                Card(CardSignaturePack(CardOrdinalSignature.TEN, CardSymbolSignature.HEART)),
            )
        strategy.score(cards) shouldBe 21
    }
})
