package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("카드 컬렉션 테스트")
class CardsTest {

    @Test
    fun `카드 추가 기능이 정상 동작`() {
        // given
        val cards = Cards()
        val card = Card(CardSymbol("하트"), CardNumber.ONE)

        // when
        assertThat(cards.contains(card)).isFalse
        cards.addOne(card)

        // then
        assertThat(cards.contains(card)).isTrue
    }

    @Test
    fun `카드 제거 기능이 정상 동작`() {
        // given
        val card = Card(CardSymbol("하트"), CardNumber.ONE)
        val cards = Cards(mutableListOf(card))

        // when
        assertThat(cards.contains(card)).isTrue
        cards.removeOne()

        // then
        assertThat(cards.contains(card)).isFalse
        assertThat(cards.size).isEqualTo(0)
    }

    @Test
    fun `카드 제거시 남아 있는 카드 개수가 0개이면 예외 발생`() {
        // given, when, then
        val exception = assertThrows<IllegalArgumentException> { Cards().removeOne() }
        assertThat(exception.message).isEqualTo("카드 개수가 0개 입니다.")
    }
}
