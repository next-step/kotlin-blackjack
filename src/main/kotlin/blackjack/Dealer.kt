package blackjack

import blackjack.card.deck.BlackJackCardDeck

class Dealer(
    private val cardDeck: BlackJackCardDeck
) {
    fun provideCard(players: List<Player>, numberOfCards: Int) {
        require(numberOfCards > 0) { "나누어주려는 카드의 갯수는 0보다 커야합니다." }

        players.forEach { player ->
            repeat(numberOfCards) {
                player.addCard(cardDeck.castCard())
            }
        }
    }
}
