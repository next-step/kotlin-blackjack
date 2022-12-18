package blackjack

import blackjack.view.PlayerDto

private const val INITIAL_CARD_COUNT = 2

class BlackJackGame(
    private val players: List<Player>,
    private val dealer: Dealer,
    private val deck: CardDeck,
) {
    fun start(): List<PlayerDto> = dealerInit() + playerInit()

    private fun dealerInit(): List<PlayerDto> {
        dealer.addCard(deck.deal(INITIAL_CARD_COUNT))
        return listOf(PlayerDto(dealer))
    }

    private fun playerInit() = players.map {
        val player = it.addCard(deck.deal(INITIAL_CARD_COUNT))
        PlayerDto(player)
    }

    fun addCard(name: String): PlayerDto = getPlayerBy(name)
        .addCard(deck.deal())
        .let { PlayerDto(it) }

    private fun getPlayerBy(name: String): Player {
        return players.firstOrNull { it.name == name } ?: throw IllegalArgumentException("존재하지 않는 플레이어 이름입니다.")
    }

    fun hitDealer(): Int {
        return dealer.hitUntil(deck)
    }

    fun result(): List<PlayerDto> {
        players.forEach {
            it.flip(dealer)
        }
        return listOf(PlayerDto(dealer)) + players.map { PlayerDto(it) }
    }
}
