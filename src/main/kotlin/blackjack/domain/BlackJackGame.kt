package blackjack.domain

import blackjack.view.PlayerResultDto

private const val INITIAL_CARD_COUNT = 2

class BlackJackGame(
    private val players: List<Player>,
    private val dealer: Dealer,
    private val deck: CardDeck,
) {
    fun start(): List<PlayerResultDto> = dealerInit() + playerInit()

    private fun dealerInit(): List<PlayerResultDto> {
        dealer.addCard(deck.deal(INITIAL_CARD_COUNT))
        return listOf(PlayerResultDto(dealer, dealer.firstCard()))
    }

    private fun playerInit() = players.map {
        val player = it.addCard(deck.deal(INITIAL_CARD_COUNT))
        PlayerResultDto(player)
    }

    fun addCard(name: String): PlayerResultDto = getPlayerBy(name)
        .addCard(deck.deal())
        .let { PlayerResultDto(it) }

    private fun getPlayerBy(name: String): Player {
        return players.firstOrNull { it.name == name } ?: throw IllegalArgumentException("존재하지 않는 플레이어 이름입니다.")
    }

    fun hitDealer(): Int {
        return dealer.hitUntil(deck)
    }

    fun result(): List<PlayerResultDto> {
        return BlackjackResultMaker.result(dealer, players)

    }
}
