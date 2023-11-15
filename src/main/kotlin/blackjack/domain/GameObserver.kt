package blackjack.domain

interface GameObserver {
    fun onGameStarted(participants: List<Participant>)
    fun onPlayerHits(player: Player)
    fun onDealerHits(dealer: Dealer)
    fun onGameEnded(participants: List<Participant>)
}
