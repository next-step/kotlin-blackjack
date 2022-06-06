package game.blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 덱")
internal class DeckTest {

    @Test
    fun `덱 초기화 하면 52개 카드 생성`() {
        val deck = Deck()
        Assertions.assertThat(deck.cards).hasSize(52)
    }

    @Test
    fun `덱에서 카드 가져오기`() {
        val expected = 10
        val deck = Deck()

        repeat(expected) {
            deck.takeCard()
        }

        Assertions.assertThat(deck.cards).hasSize(52 - expected)
    }
}