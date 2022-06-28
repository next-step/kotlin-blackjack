package blackjack.domain

data class Participants(
    val dealer: Dealer,
    val players: List<Player>
) {
    val all: List<Participant> by lazy {
        mutableListOf<Participant>(dealer).apply {
            addAll(players)
        }
    }

    companion object {
        fun of(
            dealerName: PlayerName,
            playerInfos: List<PlayerInfo>,
            cardDeck: CardDeck
        ) {
            val dealer = Dealer(
                dealerName.value
            )
        }
    }
}
