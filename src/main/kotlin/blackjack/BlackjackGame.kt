package blackjack

class BlackjackGame(gamers: List<UserRole>, deck: Deck) {

    private val _gamers: MutableList<UserRole> = gamers.toMutableList()
    private val cards = deck.cards.toMutableList()

    init {
        validateMinPlayer(_gamers.size)

        repeat(BASIC_RULE_COUNT) {
            _gamers.forEach {
                it.draw(cards.removeAt(TOP_CARD))
            }
        }
    }

    val gamers: List<UserRole>
        get() = _gamers.toList()


    fun play(): List<UserRole> {
        val players = _gamers.filter { !it.isDealer() }
        val results = mutableListOf<UserRole>()
        for (player in players) {
            results.add(playPlayerTurn(player))
        }

        results.add(playDealerTurn())
        return results.toList()
    }

    private fun validateMinPlayer(playerCount: Int) {
        require(BLACKJACK_PLAY_MIN_PLAYER_COUNT > playerCount) { "플레이어가 너무 많습니다" }
    }

    private fun playPlayerTurn(player: UserRole): UserRole {
        var p = player
        while (!p.isFinish()) {
            p = deal(p) as Player
        }
        return p
    }

    private fun playDealerTurn(): UserRole {
        var dealer = _gamers.first { it.isDealer() }
        while (!dealer.isFinish()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            dealer = dealer.draw(cards.removeAt(TOP_CARD))
        }
        return dealer
    }

    private fun deal(player: UserRole): UserRole {
        println("%s 님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(player.name))
        return when (readln()) {
            "y" -> player.draw(cards.removeAt(TOP_CARD))
            else -> player.stand()
        }
    }

    companion object {
        private const val BASIC_RULE_COUNT = 2
        private const val BLACKJACK_CARD_COUNT = 52
        private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
        const val TOP_CARD = 0
    }
}
