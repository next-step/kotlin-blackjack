package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    internal fun `승패를 계산한다`() {
        val outcome = GameResult(listOf(Player.Person("pobi"), Player.Person("jason")), Player.Dealer()).outcome
        // 딜러: 1승 1패
        // pobi: 1승
        // jason: 1패
        assertThat(outcome).isEqualTo(
            mapOf(
                "dealer" to listOf(Win to 1, Lose to 1),
                "pobi" to listOf(Lose to 1),
                "jason" to listOf(Win to 1)
            )
        )
    }
}
