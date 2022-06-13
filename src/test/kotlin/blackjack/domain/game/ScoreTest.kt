package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {

    @Test
    fun `스코어 계산하는 로직 테스트`() {
        val cardDeck = CardDeck()
        val player = Player(
            name = "A",
            cards = listOf(
                cardDeck.pickCardByNumber(2),
                cardDeck.pickCardByNumber(5),
                cardDeck.pickCardByNumber(9),
            )
        )

        assertThat(Score.calculateScore(player.receivedCards)).isEqualTo(16)
    }
}
