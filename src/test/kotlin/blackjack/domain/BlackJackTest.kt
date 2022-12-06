package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackTest {

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장을 뽑음")
    fun `Pull 2 cards for the first time`() {
        val players = listOf(Player("홍길동"))
        val playerWithCard = BlackJack.firstPick(players).first()

        assertThat(playerWithCard.cards.size).isEqualTo(2)
    }
}
