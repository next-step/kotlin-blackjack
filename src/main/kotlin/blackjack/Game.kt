package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput
import blackjack.view.Input
import blackjack.view.Output

class Game(val deck: Deck, val input: Input, val output: Output) {
    fun run() {
        val names = input.askParticipantNames()
        val participants = Participants from names
        participants receiveTwoCards deck
        output printInitialStatus participants
        input askDrawMoreCard participants
        output printResult participants
    }

    private infix fun Input.askDrawMoreCard(participants: Participants) {
        participants.forEach { this askDrawMoreCardIfNotBust it }
    }

    private infix fun Input.askDrawMoreCardIfNotBust(participant: Participant) {
        do {
            val answer = this askDrawMoreCard participant
            if (answer == "y") participant receive deck.draw()
            output printStatus participant
        } while (!participant.isBust() && answer == "y")
    }

    private infix fun Participants.receiveTwoCards(deck: Deck) {
        for (i in 1..2) {
            this.draw(deck)
        }
    }
}

fun main() {
    val game = Game(Deck(), ConsoleInput(), ConsoleOutput())
    game.run()
}
