package blackjack.domain.dealer

import blackjack.domain.Deck
import blackjack.domain.ParticipantName
import blackjack.domain.player.Player

class Dealer(
    private val deck: Deck,
    val name: ParticipantName = ParticipantName("딜러")
) {

    fun handCard(player: Player) {
        player.handCard(deck.draw())
    }
}
