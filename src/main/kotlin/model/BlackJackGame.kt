package model

class BlackJackGame(
    val players: Players,
    val dealer: Dealer,
    val deck: CardDeck,
) {
    fun getResult(): GameResult = GameResult.of(dealer, players)
}
