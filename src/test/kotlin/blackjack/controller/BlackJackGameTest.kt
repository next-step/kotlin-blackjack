package blackjack.controller

import blackjack.controller.BlackJackGame.Companion.DEFAULT_CARD_COUNTS
import blackjack.model.Participant
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun checkDefaultAllocatedCards() {
        val blackJackGame = BlackJackGame(participants)
        blackJackGame.allocateDefaultCards()

        blackJackGame.participants.forEach {
            it.cards.size shouldBe DEFAULT_CARD_COUNTS
        }
    }

    companion object {
        private val participants = listOf(
            Participant("Liam"),
            Participant("Noel"),
            Participant("Gem"),
            Participant("Andy"),
        )
    }
}
