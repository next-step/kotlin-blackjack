package blackjack.controller

import blackjack.controller.BlackJackGame.Companion.DEFAULT_CARD_COUNTS
import blackjack.model.Participant
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BlackJackGameTest {

    @ParameterizedTest
    @MethodSource("makeParticipants")
    fun checkDefaultAllocatedCards(participants: List<Participant>) {
        val blackJackGame = BlackJackGame(participants)

        blackJackGame.participants.forEach {
            it.cards.size shouldBe DEFAULT_CARD_COUNTS
        }
    }

    @ParameterizedTest
    @MethodSource("makeParticipants")
    fun checkAfterOneCardAllocation(participants: List<Participant>) {
        val blackJackGame = BlackJackGame(participants)
        blackJackGame.allocateOneCard(participants[0])

        participants[0].cards.size shouldBe (DEFAULT_CARD_COUNTS + 1)
    }

    companion object {
        @JvmStatic
        fun makeParticipants() = listOf(
            Arguments.of(
                listOf(
                    Participant("Liam"),
                    Participant("Noel"),
                    Participant("Gem"),
                    Participant("Andy"),
                )
            )
        )
    }
}
