package blackjack.domain

import org.junit.jupiter.api.Test

class BlackJackCardTest {
    @Test
    fun `카드를 숫자(인덱스)로 생성한다`() {
        val card = BlackJackCard(CardType.SPADE, CardInfo.CARD_5)
        println(card)
    }
}
