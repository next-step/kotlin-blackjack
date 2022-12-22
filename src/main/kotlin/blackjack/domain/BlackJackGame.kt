package blackjack.domain

class BlackJackGame(
    val deck: Deck = Deck(),
    val dealer: Dealer = Dealer(),
    var players: List<Player> = emptyList(),
    playerResults: List<PlayerResult> = emptyList()
) {
    private val _playerResults: MutableList<PlayerResult> = playerResults.toMutableList()
    val playerResults: List<PlayerResult>
        get() = _playerResults.toList()

    private var isDealerDrawn: Boolean = false

    fun setInitDealer() {
        deck.drawInitCards().values.forEach {
            dealer.hit(it)
        }
    }

    fun setInitPlayers(names: List<String>) {
        this.players = names.map { Player(it, deck.drawInitCards()) }
    }

    fun calculateResult() {
        val notBustPlayers = players - playerResults.map { it.player }.toSet()
        notBustPlayers.forEach {
            val result = PlayerResult.from(it, dealer)
            _playerResults.add(result)
        }
    }

    fun addPlayerResultWhenBust(player: Player) {
        val playerResult = ResultStatus.LOSE
        _playerResults.add(PlayerResult(player, ResultStatus.LOSE))
        dealer.calculateResult(playerResult)
    }

    fun playDealer() {
        val isHit = dealer.isHit()
        if (isHit) dealer.hit(deck.draw())
        isDealerDrawn = true
    }

    fun isDealerDrawn() = isDealerDrawn
}
