package blackjack.domain

class BlackJackResult(_players: List<Player>) {
    val players = _players
        .onlyNotBusted()
        .sortBySum()

    private fun List<Player>.onlyNotBusted() = filterNot { it.hands.isBusted() }

    private fun List<Player>.sortBySum() = sortedByDescending { it.hands.sum }
}
