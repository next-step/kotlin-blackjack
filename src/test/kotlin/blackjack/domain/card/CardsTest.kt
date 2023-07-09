package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    fun `카드의 점수 합을 계산할 수 있다`() {
        //에이스 없는 조합
        val withoutAceCards = Cards(mutableListOf(
            Card(CardNumber.TWO, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.HEART)
        ))

        //에이스 있는 조합
        val oneAceCards1 = Cards(mutableListOf(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.HEART)
        ))
        val oneAceCards2 = Cards(mutableListOf(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.NINE, CardSymbol.HEART),
            Card(CardNumber.JACK, CardSymbol.HEART)
        ))
        val twoAceCards = Cards(mutableListOf(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.ACE, CardSymbol.HEART),
            Card(CardNumber.JACK, CardSymbol.HEART)
        ))
        val fourAceCards = Cards(mutableListOf(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.ACE, CardSymbol.DIAMOND),
            Card(CardNumber.ACE, CardSymbol.HEART),
            Card(CardNumber.ACE, CardSymbol.CLOVER)
        ))

        withoutAceCards.getScore() shouldBe 12
        oneAceCards1.getScore() shouldBe 21
        oneAceCards2.getScore() shouldBe 20
        twoAceCards.getScore() shouldBe 12
        fourAceCards.getScore() shouldBe 14
    }
}
