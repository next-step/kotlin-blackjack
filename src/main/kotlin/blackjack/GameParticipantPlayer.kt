package blackjack

class GameParticipantPlayer(
    name: String,
    cards: List<Card> = emptyList(),
    betAmount: Int
) : GameParticipant(name, cards, betAmount) {
    override fun isNotAllowedDealing(): Boolean = this.isBust || this.isBlackjack() || this.isSameMaxScore()

    override fun receiveCard(card: Card): GameParticipantPlayer = GameParticipantPlayer(
        name = this.name,
        cards = this.cards + card,
        betAmount = this.betAmount
    )
}
