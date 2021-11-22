package blackJack.domain.player

import blackJack.domain.card.PlayingCard

class GamePlayers(
    private val players: Players,
    private val dealer: Dealer
) {

    fun getDealer(): Dealer = dealer

    fun getPlayers(): Players = players

    fun startBlackJack(playingCard: PlayingCard) {
        players.forEach { player ->
            receiveInitCards(INIT_RECEIVE_CARD_NUMBER, player, playingCard)
        }
        receiveInitCards(INIT_RECEIVE_CARD_NUMBER, dealer, playingCard)
    }

    private tailrec fun receiveInitCards(n: Int, state: State, playingCard: PlayingCard) {
        if (n > 0) {
            state.receiveCard() {
                playingCard.drawCard()
            }
            receiveInitCards(n - 1, state, playingCard)
        }
    }

    companion object {
        fun enterGameRoom(names: String): GamePlayers {
            val players = Players.of(names)
            val dealer = Dealer()
            return GamePlayers(players, dealer)
        }

        private const val INIT_RECEIVE_CARD_NUMBER = 2
    }
}
