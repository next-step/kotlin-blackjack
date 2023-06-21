package blackjack.domain

import blackjack.domain.dsl.buildGame

class Game(val players: Players, private val deck: Deck) {

    fun dealInitialCards(capacity: Int = 2) {
        players.forEach { player ->
            player.takeIf { it.isAddable() }?.addCardAll(deck.pick(capacity))
        }
    }

    fun deal(player: Player, capacity: Int = 1) {
        player.takeIf { players.contains(player) && player.isAddable() }
            ?.addCardAll(deck.pick(capacity))
            ?: throw IllegalArgumentException("플레이어가 유효하지 않습니다. Player: $player")
    }

    companion object {
        fun from(requestNames: List<String>): Game =
            buildGame {
                players {
                    requestNames.forEach { name(it) }
                }
                deck {
                    aceCards(SymbolType.DIAMOND, SymbolType.HEART, SymbolType.CLOVER, SymbolType.SPADE)
                    numberCards {
                        SymbolType.values().forEach {
                            NumberCard.MIN_LIMIT..NumberCard.MAX_LIMIT from it
                        }
                    }
                    faceCards {
                        SymbolType.values().forEach {
                            it to FaceType.KING and FaceType.QUEEN and FaceType.JACK
                        }
                    }
                }
            }
    }
}
