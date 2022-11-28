package blackjack

import blackjack.view.PlayerDto

private const val INITIAL_CARD_COUNT = 2

class BlackJackGame(
    private val players: List<Player>,
    private val deck: CardDeck,
) {
    fun start(): List<PlayerDto> {
        return players.map {
            val player = it.addCard(deck.deal(INITIAL_CARD_COUNT))
            PlayerDto(player)
        }
    }

    fun addCard(name: String): PlayerDto = getPlayerBy(name)
        .addCard(deck.deal())
        .let { PlayerDto(it) }

    private fun getPlayerBy(name: String): Player {
        return players.firstOrNull { it.name == name } ?: throw IllegalArgumentException("존재하지 않는 플레이어 이름입니다.")
    }

    fun result(): List<PlayerDto> {
        return players.map { PlayerDto(it) }
    }
}
