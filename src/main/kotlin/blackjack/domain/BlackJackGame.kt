package blackjack.domain

class BlackJackGame(private val players: List<Player>, private val deck: Deck) {
    init {
        require(players.size >= MINIMUM_PLAYERS) { "게임 진행을 위해서는 최소한 ${MINIMUM_PLAYERS}명이 필요합니다," }
    }

    fun askPlayerHit(player: Player, asker: (Player) -> Boolean): Boolean {
        return asker(player)
    }

    fun hitPlayer(player: Player, deck: Deck) {
        player hit deck.draw()
    }

    fun startGame(eachStep: (Player) -> Unit, asker: (Player) -> Boolean): BlackJackResult {
        players.forEach { player ->
            while (askPlayerHit(player, asker)) {
                player hit deck.draw()
                if (player.hands.isBusted()) {
                    break
                }
                eachStep(player)
            }
            eachStep(player)
        }
        return BlackJackResult(players)
    }

    fun startWithTwoCards() {
        players.forEach { player ->
            repeat(STARTING_HAND) { player.drawCard(deck) }
        }
    }

    companion object {
        private const val MINIMUM_PLAYERS = 1
        private const val STARTING_HAND = 2
    }
}
