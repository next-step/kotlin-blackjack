package blackjack.controller

import blackjack.controller.BlackJackGame.Companion.DEFAULT_CARD_COUNTS
import blackjack.model.Card
import blackjack.model.CardInfo
import blackjack.model.CardType
import blackjack.model.Participant
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
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

    @Test
    fun `카드를 더 뽑을 수 있는지 참여자인지 확인하는 용도로 현재 가지고 있는 카드의 합을 계산`() {
        val participantScore20 = Participant(
            "Paul",
            mutableListOf(
                Card(CardType.Clover, CardInfo.Ace),
                Card(CardType.Clover, CardInfo.Nine),
                Card(CardType.Clover, CardInfo.King),
            )
        )

        val participantScore21 = Participant(
            "Ringo",
            mutableListOf(
                Card(CardType.Clover, CardInfo.Six),
                Card(CardType.Clover, CardInfo.Seven),
                Card(CardType.Clover, CardInfo.Eight),
            )
        )

        participantScore20.isPossibleToTakeMoreCard() shouldBe true
        participantScore21.isPossibleToTakeMoreCard() shouldBe false
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
