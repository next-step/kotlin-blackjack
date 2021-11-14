package blackject

import blackject.model.card.CardNumber
import blackject.model.card.CardType
import blackject.model.card.CardsDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    @DisplayName("전체 카드 리스트 개수 확인")
    fun `check card total number`() {
        val expectedNumber = CardNumber.values().size * CardType.values().size
        val origin = CardsDeck.originCardTotalList

        assertThat(origin.size).isEqualTo(expectedNumber)
    }

    @Test
    @DisplayName("원하는 개수의 카드를 받을 수 있는지 확인")
    fun `take card number`() {
        val takeNumber = 3

        val card = CardsDeck.takeCard(takeNumber)

        assertThat(card.size).isEqualTo(takeNumber)
    }
}
