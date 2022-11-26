package blackjack

import blackjack.view.PlayerDto

class BlackJackGame(
    private val players: List<Player>,
    private val deck: CardDeck,
) {
    fun start(): List<PlayerDto> {
        return players.map {
            PlayerDto(it.apply {
                addCard(deck.deal(2))
            })
        }
    }

    fun addCard(name: String): PlayerDto = getByName(name)
        .apply { addCard(deck.deal()) }
        .let { PlayerDto(it) }

    private fun getByName(name: String): Player {
        return players.firstOrNull { it.name == name } ?: throw IllegalArgumentException("존재하지 않는 플레이어 이름입니다.")
    }

    fun result(): List<PlayerDto> {
        return players.map { PlayerDto(it) }
    }
}
