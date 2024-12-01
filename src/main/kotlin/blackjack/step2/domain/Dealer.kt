package blackjack.step2.domain

class Dealer(
    private val cardPicker: CardPicker,
) {
    fun dealFirstCards(players: Players): PlayerCards {
        val playerCards =
            players.names.map {
                PlayerCard.of(it, cardPicker.pick(INITIAL_DEAL_COUNT))
            }
        return PlayerCards.of(playerCards)
    }

    fun dealCard(playerCard: PlayerCard): PlayerCard {
        val card =
            generateSequence { cardPicker.pick() }
                .first { it !in playerCard.allCards }
        return playerCard.addCard(card)
    }

    companion object {
        private const val INITIAL_DEAL_COUNT = 2
    }
}
