package blackjack.domain

import blackjack.domain.dsl.buildGame

class Game(val players: Players, private val deck: Deck, val dealer: Dealer) {

    fun dealInitialCards() = players.forEach { player ->
        player.takeIf { it.isAddable() }?.addCardAll(deck.pick(INIT_TAKE_SIZE))
    }.also {
        dealer.takeIf { it.isAddable() }?.addCardAll(deck.pick(INIT_TAKE_SIZE))
    }

    fun deal(player: Player, capacity: Int = 1) {
        require(player.isAddable()) {
            "플레이어가 더 이상 카드를 받을 수 없습니다. Player: $player"
        }

        when {
            player is Gamer && players.contains(player) -> player
            player is Dealer && dealer == player -> player
            else -> throw IllegalArgumentException("지정된 플레이어가 게임에 참여하지 않았습니다. Player: $player")
        }.addCardAll(deck.pick(capacity))
    }

    fun dealCardsToDealerAndTo(block: () -> Unit) {
        if (dealer.isAddable()) {
            deal(player = dealer)
            block()
        }
    }

    companion object {
        const val INIT_TAKE_SIZE = 2
        const val THRESHOLD = 21

        fun from(request: List<Pair<String, Double>>): Game =
            buildGame {
                dealer()
                players {
                    request.forEach {
                        name(it.first)
                        bet(it.second)
                    }
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
