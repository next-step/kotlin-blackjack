package blackJack.domain.player

import blackJack.domain.card.Cards

class Participants(val players: Players, val dealer: Dealer) {
    fun receiveInitialCards(initialCards: () -> Cards) {
        players.receiveInitialCards(initialCards)
        dealer.receiveInitialCards(initialCards.invoke())
    }

    companion object {
        fun createParticipants(playerList: List<Player>): Participants {
            val players = Players.createPlayers(playerList)
            val dealer = Dealer.createDealer()
            return Participants(players, dealer)
        }
    }
}
